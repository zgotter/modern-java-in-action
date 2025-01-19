package modern_java_in_action.ch16.v1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class BestPriceFinder {
    private final List<Shop> shops = Arrays.asList(
        new Shop("BestPrice"),
        new Shop("LetsSaveBig"),
        new Shop("MyFavoriteShop"),
        new Shop("BuyItAll")
//        new Shop("ShopEasy")
    );

    private final Executor executor = Executors.newFixedThreadPool(
            Math.min(shops.size(), 100), // 상점 수 만큼의 스레드를 갖는 풀 생성
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true); // 프로그램 종료를 방해하지 않는 데몬 스레드 사용
                    return t;
                }
            }
    );

    public List<String> findPricesSequential(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(),  shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(),  shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public List<String> findPricesFuture(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync( // CompletableFuture로 각각의 가격을 비동기적으로 계산
                        () -> String.format("%s price is %.2f", shop.getName(),  shop.getPrice(product))
                )).collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join) // 모든 비동기 동작이 끝나기를 기다린다.
                .collect(Collectors.toList());
    }

    public List<String> findPricesFuture2(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync( // CompletableFuture로 각각의 가격을 비동기적으로 계산
                        () -> String.format("%s price is %.2f", shop.getName(),  shop.getPrice(product)),
                        executor
                )).collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join) // 모든 비동기 동작이 끝나기를 기다린다.
                .collect(Collectors.toList());
    }
}
