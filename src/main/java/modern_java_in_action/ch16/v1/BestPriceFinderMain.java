package modern_java_in_action.ch16.v1;

public class BestPriceFinderMain {

    private static BestPriceFinder bestPriceFinder = new BestPriceFinder();

    public static void main(String[] args) {
//        long start = System.nanoTime();
//        System.out.println(bestPriceFinder.findPricesSequential("myPhone27S"));
//        long duration = (System.nanoTime() - start) / 1_000_000;
//        System.out.println("Done in " + duration + " msecs");

//        long start = System.nanoTime();
//        System.out.println(bestPriceFinder.findPricesParallel("myPhone27S"));
//        long duration = (System.nanoTime() - start) / 1_000_000;
//        System.out.println("Done in " + duration + " msecs");

//        long start = System.nanoTime();
//        System.out.println(bestPriceFinder.findPricesFuture("myPhone27S"));
//        long duration = (System.nanoTime() - start) / 1_000_000;
//        System.out.println("Done in " + duration + " msecs");

        long start = System.nanoTime();
        System.out.println(bestPriceFinder.findPricesFuture2("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }
}
