package main.java.modern_java_in_action.ch05;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static main.java.modern_java_in_action.ch05.Dish.menu;
import static main.java.modern_java_in_action.ch05.Dish.specialMenu;

public class Ch05_02_slicing {
    public static void main(String[] args) {
        // 5.2.1
        List<Dish> filteredMenu = specialMenu.stream()
                .filter(d -> d.getCalories() < 320)
                .collect(toList());
        System.out.println(filteredMenu);

        List<Dish> slicedMenu1 = specialMenu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .collect(toList());
        System.out.println(slicedMenu1);

        List<Dish> slicedMenu2 = specialMenu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .collect(toList());
        System.out.println(slicedMenu2);

        // 5.2.2
        List<Dish> dishes = specialMenu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(toList());
        System.out.println(dishes);

        // 5.2.3
        List<Dish> dishes2 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());
        System.out.println(dishes2);

        // 퀴즈 5-1
        List<Dish> meet2 = menu.stream()
                .filter(d -> d.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(toList());
        System.out.println(meet2);


        List<Dish> specialMenu2 = Arrays.asList(
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("french fries", true, 530, Dish.Type.OTHER)
        );

        System.out.println("-----------");
        List<Dish> filteredMenu2 = specialMenu2.stream()
                .filter(d -> d.getCalories() < 320)
                .collect(toList());
        System.out.println(filteredMenu2);

        List<Dish> slicedMenu12 = specialMenu2.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .collect(toList());
        System.out.println(slicedMenu12);
    }
}
