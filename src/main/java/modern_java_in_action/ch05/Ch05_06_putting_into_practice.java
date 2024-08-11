package main.java.modern_java_in_action.ch05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.joining;

public class Ch05_06_putting_into_practice {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );

        // 예제 5-1
        System.out.println("예제 5-1");
        List<Transaction> tr2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        System.out.println(tr2011);

        // 예제 5-2
        System.out.println("예제 5-2");
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());
        System.out.println(cities);

        Set<String> cities2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(toSet());
        System.out.println(cities2);

        // 예제 5-3
        System.out.println("예제 5-3");
        List<Trader> cambridgeTraders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        System.out.println(cambridgeTraders);

        // 예제 5-4
        System.out.println("예제 5-4");
        String traderNamesStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName()) // 모든 Trader의 이름을 문자열 스트림으로 추출
                .distinct() // 중복된 이름 제거
                .sorted() // 이름을 알파벳 순으로 정렬
                .reduce("", (n1, n2) -> n1 + n2); // 각각의 이름을 하나의 문자열로 연결
        System.out.println(traderNamesStr);

        String traderNamesStr2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining());
        System.out.println(traderNamesStr2);


        // 예제 5-5
        System.out.println("예제 5-5");
        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(milanBased);

        // 예제 5-6
        System.out.println("예제 5-6");
        transactions.stream()
            .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
            .map(Transaction::getValue)
            .forEach(System.out::println);

        // 예제 5-7
        System.out.println("예제 5-7");
        Optional<Transaction> highestTransaction = transactions.stream()
            .reduce((t1, t2) -> t1.getValue() > t2.getValue() ? t1 : t2);
        highestTransaction.ifPresent(System.out::println);

        Optional<Transaction> highestTransaction2 = transactions.stream()
            .max(comparing(Transaction::getValue));
        highestTransaction2.ifPresent(System.out::println);

        Optional<Integer> highestTransactionValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        highestTransactionValue.ifPresent(System.out::println);

        // 예제 5-8
        System.out.println("예제 5-8");
        Optional<Transaction> smallestTransaction = transactions.stream()
            .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        smallestTransaction.ifPresent(System.out::println);

        Optional<Transaction> smallestTransaction2 = transactions.stream()
                .min(comparing(Transaction::getValue));
        smallestTransaction2.ifPresent(System.out::println);

        Optional<Integer> smallestTransactionValue = transactions.stream()
            .map(Transaction::getValue)
            .reduce(Integer::min);
        smallestTransactionValue.ifPresent(System.out::println);


    }
}
