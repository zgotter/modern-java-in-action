package modern_java_in_action.ch19;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CustomDefineStream {

    public static void main(String[] args) {
//        System.out.println(primes(5).collect(Collectors.toList()));

//        IntStream numbers = numbers();
//        int head = head(numbers);
//        IntStream filtered = tail(numbers).filter(n -> n % head != 0);

        IntStream numbers = numbers();
        System.out.println(primes2(numbers));
    }

    public static Stream<Integer> primes(int n) {
        return Stream.iterate(2, i -> i + 1)
                .filter(CustomDefineStream::isPrime)
                .limit(n);
    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    static IntStream numbers()  {
        return IntStream.iterate(2, n -> n + 1);
    }

    static int head(IntStream numbers) {
        return numbers.findFirst().getAsInt();
    }

    static IntStream tail(IntStream numbers) {
        return numbers.skip(1);
    }

    static IntStream primes2(IntStream numbers) {
        int head = head(numbers);
        return IntStream.concat(
                IntStream.of(head),
                primes2(tail(numbers).filter(n -> n % head != 0))
        );
    }
}
