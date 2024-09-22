package main.java.modern_java_in_action.ch05;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static main.java.modern_java_in_action.ch05.Dish.menu;
import static java.util.stream.Collectors.toList;

public class Ch05_07_integer_stream {
    public static void main(String[] args) {
        // boxing cost
        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println(calories);

//        int calories2 = menu.stream()
//                .map(Dish::getCalories)
//                .sum();

        int calories3 = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(calories3);

        IntStream intStream = menu.stream().mapToInt(Dish::getCalories); // 스트림을 숫자 스트림으로 변환
        Stream<Integer> stream = intStream.boxed(); // 숫자 스트림을 스트림으로 변환
        System.out.println(intStream);

        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max = maxCalories.orElse(1); // 값이 없을 때 기본 최댓값을 명시적으로 설정
        System.out.println(max);

        // 5.7.2
        IntStream evenNumbers = IntStream.rangeClosed(1, 100) // [1, 100] 의 범위를 나타냄
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());

        IntStream evenNumbers2 = IntStream.range(1, 100) // (1, 100) 의 범위를 나타냄
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers2.count());

        // 5.7.3
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                    IntStream.rangeClosed(a, 100)
                            .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                            .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a*a + b*b)})
                );
        pythagoreanTriples.limit(5).
                forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        Stream<double[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                ) // 만들어진 세 수
                .filter(t -> t[2] % 1 == 0); // 세 수의 세 번째 요소는 반드시 정수여야 한다.
        pythagoreanTriples2.limit(5).
                forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }
}
