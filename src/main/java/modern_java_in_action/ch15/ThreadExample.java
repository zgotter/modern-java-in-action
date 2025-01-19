package modern_java_in_action.ch15;

import static modern_java_in_action.ch15.Functions.f;
import static modern_java_in_action.ch15.Functions.g;

public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        int x = 1337;
        Result result = new Result();

        Thread t1 = new Thread(() -> {
            result.left = f(x);
        });
        Thread t2 = new Thread(() -> {
            result.right = g(x);
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(result.left + result.right);

    }

    private static class Result {
        private int left;
        private int right;
    }
}
