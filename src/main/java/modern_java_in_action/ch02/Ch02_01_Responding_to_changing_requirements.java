package main.java.modern_java_in_action.ch02;


import main.java.modern_java_in_action.ch02.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ch02_01_Responding_to_changing_requirements {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
        );

        // try1
        List<Apple> greenApplesTry1 = filterGreenApples(inventory);
        System.out.println(greenApplesTry1);

        // try2
        List<Apple> greenApplesTry2 = filterApplesByColor(inventory, Color.GREEN);
        System.out.println(greenApplesTry2);
        List<Apple> redApplesTry2 = filterApplesByColor(inventory, Color.RED);
        System.out.println(redApplesTry2);

        // try3
        List<Apple> greenApplesTry3 = filterApples(inventory, Color.GREEN, 0, true);
        System.out.println(greenApplesTry3);
        List<Apple> heavyApplesTry3 = filterApples(inventory, null, 150, false);
        System.out.println(heavyApplesTry3);
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (Color.GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (
                    (flag && apple.getColor().equals(color)) ||
                    (!flag && apple.getWeight() > weight)
            ) {
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
