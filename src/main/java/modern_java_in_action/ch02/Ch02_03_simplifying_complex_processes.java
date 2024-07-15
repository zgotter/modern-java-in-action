package main.java.modern_java_in_action.ch02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ch02_03_simplifying_complex_processes {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
        );

        int start = 1;
        int end = 10;

        // 연속된 정수 리스트 생성
        List<Integer> numbers = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            numbers.add(i);
        }

        // Try5
        List<Apple> redApplesTry5 = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return Color.RED.equals(apple.getColor());
            }
        });
        System.out.println(redApplesTry5);

        // Try6
        List<Apple> resultTry6 = filterApples(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));
        System.out.println(resultTry6);

        // Try7
        List<Apple> redApplesTry7 = filter(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));
        System.out.println(redApplesTry7);
        List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
        System.out.println(evenNumbers);
    }

    public interface Predicate<T> {
        boolean test(T t);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e: list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public interface ApplePredicate {
        boolean test (Apple apple);
    }
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static class Apple {
        int weight = 0;
        Color color = Color.GREEN;

        public Apple(int weight, Color color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return String.format("Apple{color='%s', weight=%d}", color, weight);
        }
    }
}
