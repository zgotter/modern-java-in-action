package modern_java_in_action.ch16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class BestPriceFinder {

    private final List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy"));

    private final Executor executor = Executors.newFixedThreadPool(shops.size(), (Runnable r) -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public List<String> findPricesSequential(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice(product)) // 각 상점에서 할인 전 가격 얻기
                .map(Quote::parse) // 상점에서 반환한 문자열을 Quote 객체로 변환
                .map(Discount::applyDiscount) // Discount 서비스를 이용해서 각 Quote 에 할인을 적용
                .collect(Collectors.toList());
    }

    public List<String> findPricesFuture(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(
                        () -> Discount.applyDiscount(quote), executor
                )))
                .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product), executor
                ))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(
                        () -> Discount.applyDiscount(quote), executor
                )));
    }

    public void printPricesStream(String product) {
        long start = System.nanoTime();
        CompletableFuture[] futures = findPricesStream(product)
                .map(f -> f.thenAccept(s -> System.out.println(s + " (done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }

    public List<String> findPricesFutureInUSD(String product) {
        List<CompletableFuture<Double>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            CompletableFuture<Double> futurePriceInUSD =
                CompletableFuture.supplyAsync(() -> shop.getPriceDouble(product))
                .thenCombine(
                    CompletableFuture.supplyAsync(
                        () ->  ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD))
                            .completeOnTimeout(ExchangeService.DEFAULT_RATE, 1, TimeUnit.SECONDS),
                        (price, rate) -> price * rate
                    )
                .orTimeout(3, TimeUnit.SECONDS);
            priceFutures.add(futurePriceInUSD);
        };

        List<String> prices = priceFutures.stream()
                .map(CompletableFuture::join)
                .map(price -> /*shop.getName() +*/ " price is " + price)
                .collect(Collectors.toList());
        return prices;
    }

    public List<String> findPricesInUSDJava7(String product) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Double>> pricesFutures = new ArrayList<>();
        for (Shop shop : shops) {
            final Future<Double> futureRate = executor.submit(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    return ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD);
                }
            });

            Future<Double> futurePriceInUSD = executor.submit(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    double priceInEUR = shop.getPriceDouble(product);
                    return priceInEUR * futureRate.get();
                }
            });

            pricesFutures.add(futurePriceInUSD);
        }

        List<String> prices = new ArrayList<>();
        for (Future<Double> priceFuture : pricesFutures) {
            try {
                prices.add("price  is " + priceFuture.get());
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return prices;
    }
}
