package main.java.modern_java_in_action.ch03;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.ToIntBiFunction;

public class Ch03_05_type_checking_type_inference_constraint {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
        );
        List<Apple> heavierThan150g = filter(inventory, (Apple a) -> a.getWeight() > 150);
        System.out.println(heavierThan150g);

        // 3.5.2
        Callable<Integer> c = () -> 42;
        PrivilegedAction<Integer> p = () -> 42;

        Comparator<Apple> c1 = (Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight();
        ToIntBiFunction<Apple, Apple> c2 = (Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight();
        BiFunction<Apple, Apple, Integer> c3 = (Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight();

        List<String> listOfStrings = new ArrayList<>();
        List<Integer> listOfIntegers = new ArrayList<>();

        List<String> list = new ArrayList<>();

        // Predicate는 boolean 반환값을 갖는다.
        Predicate<String> p1 = s -> list.add(s);
        Predicate<String> p2 = list::add;

        // Consumer는 void 반환값을 갖는다.
        Consumer<String> b1 = s -> list.add(s);
        Consumer<String> b2 = list::add;

        List<Apple> greenApples = filter(inventory, apple -> Color.GREEN.equals(apple.getColor()));

        // 형식을 추론하지 않음
        Comparator<Apple> ca = (Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight();

        // 형식을 추론함
        Comparator<Apple> cb = (a1, a2) -> a1.getWeight() - a2.getWeight();

        int portNumber = 1337;
        Runnable ra = () -> System.out.println(portNumber);
        // portNumber = 31337;
    }

    public void execute(Runnable runnable) {
        runnable.run();
    }

    @FunctionalInterface
    interface Action {
        void act();
    }

//    public void execute(Action<T> action) {
//        action.act();
//    }

    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T t: list) {
            if (p.test(t)) {
                results.add(t);
            }
        }
        return results;
    }
}
