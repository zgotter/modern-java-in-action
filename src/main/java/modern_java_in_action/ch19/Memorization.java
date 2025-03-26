package modern_java_in_action.ch19;

import java.util.HashMap;
import java.util.Map;

public class Memorization {

    final Map<Range, Integer> numberOfNodes = new HashMap<>();

    Integer computeNumberOfNodesUsingCache(Range range) {
        Integer result = numberOfNodes.get(range);
        if (result != null) {
            return result;
        }
        result = computeNumberOfNodes(range);
        numberOfNodes.put(range, result);
        return result;
    }

    Integer computeNumberOfNodesUsingCache2(Range range) {
        return numberOfNodes.computeIfAbsent(range, this::computeNumberOfNodes);
    }

    Integer computeNumberOfNodes(Range range) {
        return 1;
    }

    static class Range {

    }
}


