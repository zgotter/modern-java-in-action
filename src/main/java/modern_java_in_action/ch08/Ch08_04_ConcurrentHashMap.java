package modern_java_in_action.ch08;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Ch08_04_ConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        long parallelismThreshold = 1;
        Optional<Long> maxValue = Optional.ofNullable(map.reduceValues(parallelismThreshold, Long::max));
        System.out.println(maxValue);
    }
}
