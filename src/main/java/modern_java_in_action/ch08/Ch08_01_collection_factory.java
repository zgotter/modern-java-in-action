package modern_java_in_action.ch08;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ch08_01_collection_factory {
    public static void main(String[] args) {
        List<String> friends = new ArrayList<>();
        friends.add("Raphael");
        friends.add("Olivia");
        friends.add("Thibaut");

        List<String> friends2 = Arrays.asList("Raphael", "Olivia", "Thibaut");

        List<String> friends3 = Arrays.asList("Raphael", "Olivia");
        System.out.println(friends3);
        
        friends3.set(0, "Richard");
        System.out.println(friends3);
        
//        friends3.add("Thibaut");
//        System.out.println(friends3);

        Set<String> friends4 = new HashSet<>(Arrays.asList("Raphael", "Olivia", "Thibaut"));
        System.out.println(friends4);

        Set<String> friends5 = Stream.of("Raphael", "Olivia", "Thibaut")
                .collect(Collectors.<String>toSet());
        System.out.println(friends5);

        List<String> friends6 = List.of("Raphael", "Olivia", "Thibaut");
        System.out.println(friends6);

//        friends6.add("Chih-Chun");
//        friends6.set(0, "Chih-Chun");

        Set<String> friends7 = Set.of("Raphael", "Olivia", "Thibaut");
        System.out.println(friends7);

//        Set<String> friends8 = Set.of("Raphael", "Olivia", "Olivia");
//        System.out.println(friends8);

        Map<String, Integer> ageOfFriends = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);
        System.out.println(ageOfFriends);

        Map<String, Integer> ageOfFriends2 = Map.ofEntries(
                Map.entry("Raphael", 30),
                Map.entry("Olivia", 25),
                Map.entry("Thibaut", 26)
        );
        System.out.println(ageOfFriends2);

        List<String> actors = List.of("Keanu", "Jessica");
        actors.set(0, "Brad");
        System.out.println(actors);
    }
}
