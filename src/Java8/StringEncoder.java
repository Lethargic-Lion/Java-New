package Java8;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.lang.IO.println;

public class StringEncoder {

    public static String encodeString(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder encoded = new StringBuilder();
        int count = 1;

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i - 1)) {
                count++;
            } else {
                encoded.append(count).append(input.charAt(i - 1));
                count = 1;
            }
        }

        // Append the last sequence
        encoded.append(count).append(input.charAt(input.length() - 1));

        return encoded.toString();
    }

    private static void maxRepeatedFirstOccurrence() {
        String str = "teeth";

        Optional<Character> result = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);

        if (result.isPresent()) {
            System.out.println("Maximum repeated first occurrence character: " + result.get());
        } else {
            System.out.println("No characters found.");
        }
    }

    private static void firstNonRepeatingCharacter() {
        String str  = "swiss";

        Optional<Character> result = str.chars().mapToObj(value -> (char) value)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst();

        println("Result: " + result.orElse(null));
    }

    static void main(String[] args) {
//        String input = "aaaaaeeeffffffffffnnfffffffnnnnnnnneeeeeekkkkkkkkkkkk";
//        System.out.println(encodeString(input)); // Expected: 5a3e10f2n7f8n6e12k
        maxRepeatedFirstOccurrence();
        firstNonRepeatingCharacter();

        String[] votes = {"john", "johnny", "jackie", "johnny", "john", "jackie", "jamie", "jamie", "john", "johnny", "jamie", "johnny", "john"};

        Map<String, Long> voteMap = new HashMap<>();
        for(int i=0; i<votes.length; i++){
            voteMap.put(votes[i], voteMap.getOrDefault(votes[i], 0L) + 1);
        }

        Long maxVotes = 0L;
        for(Long voteCount : voteMap.values()){
            if(voteCount > maxVotes)
                maxVotes = voteCount;
        }

        List<String> winners = new ArrayList<>();
        for(Map.Entry<String, Long> pair : voteMap.entrySet()){

            if(Objects.equals(pair.getValue(), maxVotes))
                winners.add(pair.getKey());
            println("" + pair.getKey() + " : " + pair.getValue());
        }

        Collections.sort(winners);
        println(winners.get(0));

        //using stream API
        Map<String, Long> voteMap2 = Arrays.stream(votes).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Long maxVotes2 = voteMap2.values().stream().mapToLong(x -> x).max().orElse(0L);
        String winner = String.valueOf(voteMap2.entrySet().stream().filter(e -> e.getValue() == maxVotes2).map(Map.Entry::getKey).sorted().findFirst());

        //use case for using flatMap
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("orange", "kiwi"),
                Arrays.asList("grape", "mango")
        );
        List<String> flatList = listOfLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        println(flatList);

        //use case of map vs flatMap
        List<String> words = Arrays.asList("Hello", "World");
        List<String[]> mappedList = words.stream()
                .map(word -> word.split("")) // Stream<String[]>
                .collect(Collectors.toList());
        //print mappedList values
        println("Mapped List: " + mappedList);

        String a = "abc";
        String b = new String("abc");

        //Function example
        Function<String, String> toUpperCase = String::toUpperCase;
        println(toUpperCase.apply(a)); // Output: ABC

        //Predicate example
        Predicate<String> isEqual = String::isEmpty;
        println(isEqual.test("")); // Output: true

        //Consumer example
        Consumer<String> printString = System.out::println;
        printString.accept("Hello, World!"); // Output: Hello, World!

        //Supplier example
        Supplier<String> stringSupplier = () -> "Supplied String";
        println(stringSupplier.get()); // Output: Supplied String
    }
}
