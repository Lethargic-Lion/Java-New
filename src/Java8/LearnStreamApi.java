package Java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.IO.println;

public class LearnStreamApi {
    static void main() {
        //imperative approach
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int sum = 0;
        for (int i : arr) {
            if(i % 2 == 0){
                sum += i;
            }
        }

        println(sum);

        // declarative approach using Stream API
        int streamSum = Arrays.stream(arr)
                .filter(x -> x%2 == 0)
                .sum();
        println(streamSum);

        List<String> listOfFruits = Arrays.asList("apple", "banana", "orange", "kiwi", "grape");
        String[] arrayOfFruits = {"apple", "banana", "orange", "kiwi", "grape"};

        Stream<String> stream = Arrays.stream(arrayOfFruits);
        Stream<String> stream1 = listOfFruits.stream();

        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> limit1 = Stream.iterate(2, n -> n + 1).limit(100);
        Stream<Double> limit = Stream.generate(Math::random).limit(10);

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 543, 456789, 0, 0, -1, -3456);
        List<Integer> collect = list.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x / 2)
                .distinct()
                .sorted((o1, o2) -> o2 - o1)
                .peek(IO::println)
                .collect(Collectors.toList());
        println(collect);

        sortNamesInReverseAlphabeticalOrder();
        sortNumbersInReverseDescendingOrder();
        useFlatMap();
    }

    private static void sortNamesInReverseAlphabeticalOrder(){
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Charlie");
        List<String> collect = names.stream().sorted(Comparator.reverseOrder()).peek(IO::println).collect(Collectors.toList());

        println(collect);
    }

    private static void sortNumbersInReverseDescendingOrder(){
        List<Integer> names = Arrays.asList(5, 4, 9, 6);
        List<Integer> collect = names.stream().sorted(Comparator.reverseOrder()).peek(IO::println).collect(Collectors.toList());

        println(collect);
    }

    private static void useFlatMap() {
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("orange", "kiwi"),
                Arrays.asList("grape", "melon")
        );

        List<String> flatList = listOfLists.stream()
                .peek(IO::print)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        println(flatList);
    }
}
