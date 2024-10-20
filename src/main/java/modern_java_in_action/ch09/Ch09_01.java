package modern_java_in_action.ch09;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class Ch09_01 {
    public static void main(String[] args) {
        /*
            리팩터링 예제 (3)
            - 익명 클래스를 람다 표현식으로 리팩터링
            - 람다 표현식을 메서드 참조로 리팩터링
            - 명령형 데이터 처리를 스트림으로 리팩터링
         */

        // 9.1.2 익명 클래스를 람다 표현식으로 리팩터링
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        };
        Runnable r2 = () -> System.out.println("Hello");

        // 모든 익명 클래스를 람다 표현식으로 변환할 순 없다.
        // 1. 익명 클래스에서 사용한 this 와 super는 람다 표현식에서 다른 의미를 갖는다.
        //   - 익명 클래스에서 this: 익명 클래스 자신
        //   - 람다에서 this: 람다를 감싸는 클래스
        // 2. 익명 클래스는 감싸고 있는 클래스의 변수(shadow variable)를 가릴 수 있지만 람다 표현식으로는 할 수 없다.

//        int a = 10;
//        Runnable r3 = () -> {
//            int a = 2; // 여기서 컴파일 에러 발생
//            System.out.println(a);
//        };

        int a = 10;
        Runnable r4 = new Runnable() {
            @Override
            public void run() {
                int a = 2;
                System.out.println(a);
            }
        };
        r4.run(); // 2

        // 3. 익명 클래스를 람다 표현식으로 바꾸면 콘텍스트 오버로딩에 따른 모호함이 초래될 수 있다.
        //  - 익명 클래스는 인스턴스화할 때 명시적으로 형식이 정해짐
        //  - 람다의 형식은 콘텍스트에 따라 달라짐

        // Task 를 구현하는 익명 클래스를 전달할 수 있다.
        doSomething(new Task() {
            @Override
            public void execute() {
                System.out.println("Danger danger!!");
            }
        });

        // 익명 클래스를 람다 표현식으로 바꾸면 메서드를 호출할 때 Runnable 과 Task 모두 대상 형식이 될수 있으므로 문제가 생긴다.
        // doSomething(() -> System.out.println("Danger danger!!")); // error: reference to doSomething is ambiguous

        // 명시적 형변환을 이용해서 모호함을 제거할 수 있다.
        doSomething((Task)() -> System.out.println("Danger danger!!"));

        // 9.1.3 람다 표현식을 메서드 참조로 리팩터링
        Map<Dish.CaloricLevel, List<Dish>> dishesByCaloricLevel1 = Dish.menu.stream()
                .collect(
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                            else return Dish.CaloricLevel.FAT;
                        })
                );
        System.out.println(dishesByCaloricLevel1);

        Map<Dish.CaloricLevel, List<Dish>> dishesByCaloricLevel2 = Dish.menu.stream()
                .collect(groupingBy(Dish::getCaloricLevel));
        System.out.println(dishesByCaloricLevel2);

        // comparing, maxBy
        List<Apple> inventory1 = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
        );
        inventory1.sort((Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight());
        System.out.println(inventory1);

        List<Apple> inventory2 = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
        );
        inventory2.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(inventory2);

        // sum, maximum
        // 람다 표현식 & 저수준 리듀싱 연산 조합 vs collectors API
        int totalCalories1 = Dish.menu.stream().map(Dish::getCalories).reduce(0, (c1, c2) -> c1 + c2);
        System.out.println(totalCalories1);

        int totalCalories2 = Dish.menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(totalCalories2);

        // 9.1.4 명령형 데이터 처리를 스트림으로 리팩터링
        List<String> dishNames1 = new ArrayList<>();
        for (Dish dish: Dish.menu) {
            if (dish.getCalories() > 300) {
                dishNames1.add(dish.getName());
            }
        }
        System.out.println(dishNames1);

        List<String> dishNames2 = Dish.menu.parallelStream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(dishNames2);

        // 9.1.5 코드 유연성 개선


    }

    interface Task {
        public void execute();
    }

    public static void doSomething(Runnable r) { r.run(); }
    public static void doSomething(Task a) { a.execute(); }


}
