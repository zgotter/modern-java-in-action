package main.java.modern_java_in_action.ch05;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ch05_08_make_stream {
    public static void main(String[] args) {
        // 5.8.1
        Stream<String> stream = Stream.of("Modern", "Java", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        Stream<String> emptyStream = Stream.empty();

        // 5.8.2
        String homeValue = System.getProperty("home");
        System.out.println(homeValue);

        Stream<String> homeValueStream = homeValue == null ? Stream.empty() : Stream.of(homeValue);

        Stream<String> homeValueStream2 = Stream.ofNullable(System.getProperty("home"));

        Stream<String> values = Stream.of("config", "home", "user")
                .flatMap(key -> Stream.ofNullable(System.getProperty(key)));

        // 5.8.3
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);

        // 5.8.4
        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(
                Paths.get("resources/ch05/data.txt"), Charset.defaultCharset() // 스트림은 자원을 자동으로 해제할 수 있는 AutoCloseable이므로 try-finally가 필요 없다.
            )
        ) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct() // 중복 제거
                    .count();
        } catch (IOException e) {
            System.out.println(e); // 파일을 열다가 예외가 발생하면 처리
        }
        System.out.println(uniqueWords);

        // 5.8.5
        // iterate
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        // 퀴즈 5-4
        Stream.iterate(new int[]{0, 1}, (t -> new int[]{t[1], t[0]+t[1]}))
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        Stream.iterate(new int[]{0, 1}, (t -> new int[]{t[1], t[0]+t[1]}))
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);

        // iterate with predicate
        IntStream.iterate(0, n -> n < 100, n -> n + 4)
                .forEach(System.out::println);

//        IntStream.iterate(0, n -> n + 4)
//                .filter(n -> n < 100)
//                .forEach(System.out::println);

        IntStream.iterate(0, n -> n + 4)
                .takeWhile(n -> n < 100)
                .forEach(System.out::println);

        // generate
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        // IntStream.generate
        IntStream ones = IntStream.generate(() -> 1);
        IntStream twos = IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        });

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib)
                .limit(10)
                .forEach(System.out::println);
    }
}
