package main.java.modern_java_in_action.ch05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static main.java.modern_java_in_action.ch05.Dish.menu;

public class Ch05_03_mapping {
    public static void main(String[] args) {
        // 5.3.1
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());
        System.out.println(dishNames);

        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);

        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        System.out.println(dishNameLengths);

        // 5.3.2
        // try1
        List<String[]> uniqueCharacters1 = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());
        System.out.println(uniqueCharacters1);

        // try2
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
        System.out.println(streamOfWords.collect(toList()));

        List<Stream<String>> uniqueCharacters2 = words.stream()
                .map(word -> word.split("")) // 각 단어를 개별 문자열 배열로 변환
                .map(Arrays::stream) // 각 배열을 별도의 스트림으로 생성
                .distinct()
                .collect(toList());
        System.out.println(uniqueCharacters2);

        // try3
        List<String> uniqueCharacters3 = words.stream()
                .map(word -> word.split("")) // 각 단어를 개별 문자열 배열로 변환
                .flatMap(Arrays::stream) // 생성된 스트림을 하나의 스트림으로 평면화
                .distinct()
                .collect(toList());
        System.out.println(uniqueCharacters3);

        // 퀴즈 5-2
        // problem 1
        List<Integer> numbers_1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares_1 = numbers_1.stream()
                .map(n -> n * n)
                .collect(toList());
        System.out.println(squares_1);

        // problem 2
        List<Integer> numbers_2_1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers_2_2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers_2_1.stream()
                .flatMap(i -> numbers_2_2.stream()
                    .map(j -> new int[]{i, j})
                )
                .collect(toList());
        pairs.forEach(pair -> System.out.printf("(%d, %d) ", pair[0], pair[1]));
        System.out.println();

        // problem 3
        List<int[]> pairs2 = numbers_2_1.stream()
                .flatMap(i -> numbers_2_2.stream()
                    .filter(j -> (i + j) % 3 == 0)
                    .map(j -> new int[]{i, j})
                )
                .collect(toList());
        pairs2.forEach(pair2 -> System.out.printf("(%d, %d) ", pair2[0], pair2[1]));
    }
}
