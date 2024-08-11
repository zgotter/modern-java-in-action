package main.java.modern_java_in_action.ch05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static main.java.modern_java_in_action.ch05.Dish.menu;

public class Ch05_04_search_matching {
    public static void main(String[] args) {
        // 5.4.1
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu (somewhat) vegetarian friendly!!");
        }

        // 5.4.2
        boolean isHealthy = menu.stream()
                .allMatch(dish -> dish.getCalories() < 1000);
        System.out.println(isHealthy);

        boolean isHealthy2 = menu.stream()
                .noneMatch(dish -> dish.getCalories() >= 1000);
        System.out.println(isHealthy2);

        // 5.4.3
        Optional<Dish> vegetarianDish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        System.out.println(vegetarianDish);

        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny() // Option<Dish> 반환
                .ifPresent(dish -> System.out.println(dish.getName())); // 값이 있으면 출력, 값이 없으면 아무 일도 일어나지 않음

        // 5.4.4
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
                .map(n -> n*n)
                .filter(n -> n % 3 == 0)
                .findFirst();
        firstSquareDivisibleByThree.ifPresent(System.out::println);
    }
}
