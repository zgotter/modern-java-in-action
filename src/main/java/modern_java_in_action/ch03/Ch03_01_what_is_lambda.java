package main.java.modern_java_in_action.ch03;

import java.util.Comparator;

public class Ch03_01_what_is_lambda {
    public static void main(String[] args) {
        Comparator<Apple> byWeight = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        };

        Comparator<Apple> byWeight2 = (Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight();
    }
}
