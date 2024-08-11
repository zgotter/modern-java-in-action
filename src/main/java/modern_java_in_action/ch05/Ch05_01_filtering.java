package main.java.modern_java_in_action.ch05;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static main.java.modern_java_in_action.ch05.Dish.menu;

public class Ch05_01_filtering {
    public static void main(String[] args) {
        // 5.1.1
        List<Dish> vegetarianMenu = menu.stream()
            .filter(Dish::isVegetarian) // 채식 요리인 지 확인하는 메서드 참조
            .collect(toList());
        System.out.println(vegetarianMenu);

        // 5.1.2
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }
}
