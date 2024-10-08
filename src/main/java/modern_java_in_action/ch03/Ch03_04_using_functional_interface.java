package main.java.modern_java_in_action.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ch03_04_using_functional_interface {
    public static void main(String[] args) {
        // Predicate
        Predicate<String> nonEmtpyStringPredicate = (String s) -> !s.isEmpty();
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("");
        listOfStrings.add("a");
        listOfStrings.add("");
        listOfStrings.add("b");
        listOfStrings.add("");
        listOfStrings.add("c");
        listOfStrings.add("");

        List<String> nonEmpty = filter(listOfStrings, nonEmtpyStringPredicate);
        System.out.println(nonEmpty);

        // forEach: Integer 리스트를 인수로 받아서 각 항목에 어떤 동작을 수행
        forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.println(i));
        forEach(Arrays.asList(1, 2, 3, 4, 5), System.out::println);

        // Function
        List<Integer> l = map(Arrays.asList("lambdas", "in", "action"), (String s) -> s.length());
        List<Integer> l2 = map(Arrays.asList("lambdas", "in", "action"), String::length);
        System.out.println(l);
        System.out.println(l2);

        // boxing, unboxing, autoboxing
        List<Integer> list = new ArrayList<>();
        for (int i=300; i < 400; i++) {
            list.add(i);
        }

        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        System.out.println(evenNumbers.test(1000)); // True, 박싱 없음

        Predicate<Integer> oddNumbers = (Integer i) -> i % 2 != 0;
        System.out.println(oddNumbers.test(1000)); // False, 박싱

        // exception
        BufferedReaderProcessor p = (BufferedReader br) -> br.readLine();

        Function<BufferedReader, String> f = (BufferedReader br) -> {
            try {
                return br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }

    public interface IntPredicate {
        boolean test(int t);
    }

    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t);
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t: list) {
            result.add(f.apply(t));
        }
        return result;
    }

    @FunctionalInterface
    public interface Consumer<T> {
        void accept(T t);
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t: list) {
            c.accept(t);
        }
    }

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
