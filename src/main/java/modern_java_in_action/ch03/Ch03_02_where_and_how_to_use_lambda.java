package main.java.modern_java_in_action.ch03;

import java.util.concurrent.Callable;

public class Ch03_02_where_and_how_to_use_lambda {
    public static void main(String[] args) {
        Runnable r1 = () -> System.out.println("Hello World 1");

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World 2");
            }
        };

        process(r1);
        process(r2);
        process(() -> System.out.println("Hello World 3"));

        process(() -> System.out.println("This is awesome!!"));
        process(() -> { System.out.println("This is awesome!!"); });

        // quiz 3-3
        execute(() -> {});

        // Predicate<Apple> p = (Apple a) -> a.getWeight();
    }

    public static void execute(Runnable r) {
        r.run();
    }

    public static Callable<String> fetch() {
        return () -> "Tricky example ;-)";
    }

    public interface Predicate<T> {
        boolean test(T t);
    }

    public static void process(Runnable r) {
        r.run();
    }
}
