package modern_java_in_action.ch13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Ch13_0 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 5, 1, 2, 6);
        System.out.println(numbers);
        numbers.sort(Comparator.naturalOrder());
        System.out.println(numbers);
    }
}
