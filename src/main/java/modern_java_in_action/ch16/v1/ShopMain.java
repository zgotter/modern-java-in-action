package modern_java_in_action.ch16.v1;

import java.util.concurrent.Future;

public class ShopMain {
    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();

        // 상점에 제품 가격 정보 요청
//        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        Future<Double> futurePrice = shop.getPriceAsync2("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        // 제품의 가격을 계산하는 동안 다른 상점 검색 등 다른 작업 수행
        doSomethingElse();

        try {
            // 가격 정보가 있다면 -> Future에서 가격 정보를 읽음
            // 가격 정보가 없다면 -> 가격 정보를 받을 때 까지 블록
            double price = futurePrice.get();
            System.out.printf("Price is  %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    private static void doSomethingElse() {
        System.out.println("Doing something else...");
    }
}
