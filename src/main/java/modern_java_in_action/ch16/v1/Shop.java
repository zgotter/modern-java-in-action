package modern_java_in_action.ch16.v1;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static modern_java_in_action.ch16.Util.delay;

public class Shop {

    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public String getName() {
        return name;
    }

    public Future<Double> getPriceAsync(String product) {
        // 계산 결과를 포함할 CompletableFuture 생성
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                // 다른 스레드에서 비동기적으로 계산을 수행
                double price = calculatePrice(product);

                // 오랜 시간이 걸리는 계산이 완료되면 Future에 값을 설정
                futurePrice.complete(price);
            } catch (Exception ex) {
                // 도중에 문제가 발생하면 발생한 에러를 포함시켜 Future를 종료
                futurePrice.completeExceptionally(ex);
            }

        }).start();

        // 계산 결과가 완료되길 기다리지 않고 Future를 반환
        return futurePrice;
    }

    public Future<Double> getPriceAsync2(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
