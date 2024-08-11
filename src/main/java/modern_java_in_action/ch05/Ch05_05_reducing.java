package main.java.modern_java_in_action.ch05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static main.java.modern_java_in_action.ch05.Dish.menu;

public class Ch05_05_reducing {
    public static void main(String[] args) {
        // 5.5.1
        List<Integer> numbers = Arrays.asList(4, 5, 3, 9);
        int sum = 0;
        for (int x: numbers) {
            sum += x;
        }
        System.out.println(sum);

        int sum2 = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum2);

        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println(product);

        int sum3 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum3);

        Optional<Integer> sum4 = numbers.stream().reduce(Integer::sum);
        sum4.ifPresent(System.out::println);

        // 5.5.2
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        max.ifPresent(System.out::println);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        // 퀴즈 5-3
        Integer dishCount = menu.stream()
                .map(dish -> 1)
                .reduce(0, Integer::sum);
        System.out.println(dishCount);

        Optional<Integer> dishCount2 = menu.stream()
                .map(dish -> 1)
                .reduce(Integer::sum);
        dishCount2.ifPresent(System.out::println);

        long dishCount3 = menu.stream().count();
        System.out.println(dishCount3);
    }
}
