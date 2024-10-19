package modern_java_in_action.ch08;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Ch08_03_map_processing {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Map<String, Integer> ageOfFriends = Map.ofEntries(
                Map.entry("Raphael", 30),
                Map.entry("Olivia", 25),
                Map.entry("Thibaut", 26)
        );

        for (Map.Entry<String, Integer> entry: ageOfFriends.entrySet()) {
            String friend = entry.getKey();
            Integer age = entry.getValue();
            System.out.println(friend + " is " + age + " years old");
        }

        ageOfFriends.forEach((friend, age) -> System.out.println(friend + " is " + age + " years old"));

        Map<String, String> favouriteMovies = Map.ofEntries(
                Map.entry("Raphael", "Star Wars"),
                Map.entry("Cristina", "Matrix"),
                Map.entry("Olivia", "James Bond")
        );

        favouriteMovies.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);

        Map<String, String> favouriteMovies2 = Map.ofEntries(
                Map.entry("Raphael", "Star Wars"),
                Map.entry("Olivia", "James Bond")
        );
        System.out.println(favouriteMovies2.getOrDefault("Olivia", "Matrix"));
        System.out.println(favouriteMovies2.getOrDefault("Thibaut", "Matrix"));

        Map<String, List<String>> friendsToMovies = new HashMap<>();

        String friend1 = "Raphael";
        List<String> movies = friendsToMovies.get(friend1);
        if (movies == null) {
            movies = new ArrayList<>();
            friendsToMovies.put(friend1, movies);
        }
        movies.add("Star Wars");

        System.out.println(friendsToMovies);

        Map<String, List<String>> friendsToMovies2 = new HashMap<>();
        friendsToMovies2.computeIfAbsent("Raphael", name -> new ArrayList<>())
                .add("Star Wars");
        System.out.println(friendsToMovies2);

        Map<String, List<String>> favouriteMovies3 = new HashMap<>();
        String key = "Raphael";
        String value = "Jack Reacher 2";
        if (favouriteMovies3.containsKey(key) && Objects.equals(favouriteMovies3.get(key), value)) {
            favouriteMovies3.remove(key);
        }

        favouriteMovies3.remove(key, value);

        // 8.3.6
        Map<String, String> favouriteMovies4 = new HashMap<>();
        favouriteMovies4.put("Raphael", "Star Wars");
        favouriteMovies4.put("Olivia", "james bond");
        favouriteMovies4.replaceAll((friend, movie) -> movie.toUpperCase());
        System.out.println(favouriteMovies4);

        // 8.3.7
        Map<String, String> family = Map.ofEntries(
                Map.entry("Teo", "Star Wars"),
                Map.entry("Cristina", "James Bond")
        );
        Map<String, String> friends = Map.ofEntries(
                Map.entry("Raphael", "Star Wars")
        );
        Map<String, String> everyone = new HashMap<>(family);
        everyone.putAll(friends);
        System.out.println(everyone);

        Map<String, String> family2 = Map.ofEntries(
                Map.entry("Teo", "Star Wars"),
                Map.entry("Cristina", "James Bond")
        );
        Map<String, String> friends2 = Map.ofEntries(
                Map.entry("Raphael", "Star Wars"),
                Map.entry("Cristina", "Matrix")
        );
        Map<String, String> everyone2 = new HashMap<>(family2);
        friends2.forEach((k, v) -> everyone2.merge(k, v, (movie1, movie2) -> movie1 + " & " + movie2));
        System.out.println(everyone2);

        Map<String, Long> moviesToCount = new HashMap<>();
        String movieName = "JamesBond";
//        long count = moviesToCount.get(movieName);
//        if (count == null) {
//            moviesToCount.put(movieName, 1);
//        } else {
//            moviesToCount.put(movieName, count + 1);
//        }

        Map<String, Long> moviesToCount2 = new HashMap<>();
        String movieName2 = "JamesBond";
        moviesToCount2.merge(movieName2, 1L, (cnt, increment) -> cnt + 1L);
        System.out.println(moviesToCount2);

        // quiz 8-2
        Map<String, Integer> movies2 = new HashMap<>();
        movies2.put("JamesBond", 20);
        movies2.put("Matrix", 15);
        movies2.put("Harry Potter", 5);
        Iterator<Map.Entry<String, Integer>> iterator = movies2.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() < 10) {
                iterator.remove();
            }
        }
        System.out.println(movies2);

        Map<String, Integer> movies3 = new HashMap<>();
        movies3.put("JamesBond", 20);
        movies3.put("Matrix", 15);
        movies3.put("Harry Potter", 5);
        movies3.entrySet().removeIf(entry -> entry.getValue() < 10);
        System.out.println(movies3);
    }
}
