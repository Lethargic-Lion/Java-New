package Java8;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.stream.*;

import static java.lang.IO.println;

public class PracticeStreams {

    // 1️⃣ First Non-Repeated Character
    public static Optional<Character> firstNonRepeatedChar(String s) {
        Map<Character, Long> freq = s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        return freq.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst();
    }

    // 2️⃣ Character Frequency Map
    public static Map<Character, Long> charFrequency(String s) {
        return s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    // 3️⃣ Second Highest Number
    public static Optional<Integer> secondHighest(List<Integer> nums) {
        return nums.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
    }

    // 4️⃣ Count Words
    public static long wordCount(String sentence) {
        return Arrays.stream(sentence.trim().split("\\s+"))
                .filter(w -> !w.isEmpty())
                .count();
    }

    // 5️⃣ Find Duplicates
    public static <T> List<T> duplicates(List<T> list) {
        Map<T, Long> freq = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return freq.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // 6️⃣ Square of Even Numbers
    public static List<Integer> squareEvens(List<Integer> nums) {
        return nums.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .collect(Collectors.toList());
    }

    // 7️⃣ Sort Map by Value
    public static <K, V extends Comparable<? super V>> LinkedHashMap<K, V> sortByValue(Map<K, V> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue,
                        (a, b) -> a, LinkedHashMap::new));
    }

    // 8️⃣ Longest Word
    public static Optional<String> longestWord(String sentence) {
        return Arrays.stream(sentence.trim().split("\\s+"))
                .max(Comparator.comparingInt(String::length));
    }

    // 9️⃣ Common Elements
    public static <T> List<T> intersection(List<T> a, List<T> b) {
        Set<T> setB = new HashSet<>(b);
        return a.stream()
                .filter(setB::contains)
                .distinct()
                .collect(Collectors.toList());
    }

    // 🔟 Check if Strings Are Anagrams
    public static boolean areAnagrams(String a, String b) {
        IntUnaryOperator norm = c -> Character.toLowerCase(c);
        int[] sa = a.chars().filter(Character::isLetter).map(norm).sorted().toArray();
        int[] sb = b.chars().filter(Character::isLetter).map(norm).sorted().toArray();
        return Arrays.equals(sa, sb);
    }

    // 🚀 MAIN METHOD for testing
    public static void main(String[] args) {
        System.out.println("1️⃣ First non-repeated char: " + firstNonRepeatedChar("stress").orElse('?'));
        System.out.println("2️⃣ Char frequency: " + charFrequency("banana"));
        System.out.println("3️⃣ Second highest: " + secondHighest(List.of(1, 3, 7, 5, 9, 9)).orElse(null));
        System.out.println("4️⃣ Word count: " + wordCount("Java is powerful and fun"));
        System.out.println("5️⃣ Duplicates: " + duplicates(List.of(1, 2, 3, 4, 2, 5, 1)));
        System.out.println("6️⃣ Square of evens: " + squareEvens(List.of(1, 2, 3, 4, 5, 6)));
        Map<String, Integer> map = Map.of("A", 5, "B", 2, "C", 8);
        System.out.println("7️⃣ Sort map by value: " + sortByValue(map));
        System.out.println("8️⃣ Longest word: " + longestWord("I love programming in Java").orElse("None"));
        System.out.println("9️⃣ Common elements: " + intersection(List.of(1, 2, 3, 4), List.of(3, 4, 5, 6)));
        System.out.println("🔟 Are anagrams (listen, silent): " + areAnagrams("listen", "silent"));

        List<Integer> nums = Arrays.asList(4, 1, 7, 3, 9, 2, 8, 6, 5);
        println(nums.stream().mapToInt(Integer::intValue).sum());
        println(nums.stream().mapToInt(Integer::intValue).max().orElse(-1));
        println(nums.stream().filter(x -> x % 2 == 0).toList());

        List<String> fruits = Arrays.asList("apple", "banana", "orange", "kiwik", "grape", "grape", "kiwik", "orange", "banana", "apple");
        char searchChar = 'a';
        long count = fruits.stream()
                .filter(fruit -> fruit.contains(String.valueOf(searchChar)))
                .count();
        println("Fruits containing '" + searchChar + "': " + count);

        double average = nums.stream().mapToInt(Integer::intValue).average().orElse(0);
        println("Average: " + average);
        println(fruits.stream().collect(Collectors.joining()));

        println(fruits.stream().min((s1, s2) -> s2.length() - s1.length()).orElse("NA"));
        println(nums.stream().mapToInt(Integer::intValue).reduce(1, (a, b) -> a * b));

        // Filter Primes
        List<Integer> primes = nums.stream().filter(n -> {
            if (n < 2) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        }).sorted().toList();
        println("Primes: " + primes);

        boolean containsString = fruits.stream().anyMatch(s -> s.equals("banana"));
        println(containsString);

        List<String> list1 = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        List<String> list2 = Arrays.asList("banana", "orange", "grape", "watermelon");
        List<String> commonFruits = list1.stream()
                .filter(list2::contains)
                .distinct()
                .toList();
        println("Common fruits: " + commonFruits);

        List<String> unionFruits = Stream.concat(list1.stream(), list2.stream())
                .distinct()
                .toList();
        println("Union of fruits: " + unionFruits);

        // Fruits containing only vowels
        List<String> vowelFruits = fruits.stream()
                .filter(fruit -> fruit.toLowerCase().matches("^[aeiou]+$"))
                .toList();
        println("Fruits with only vowels: " + vowelFruits);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 3, 4, 4, 4, 5, 5);
        Map<Integer, Long> collect = numbers.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        long l = collect.values().stream().mapToLong(Long::longValue).max().orElse(0);
        println("Max frequency: " + l);
        List<Integer> modes = collect.entrySet().stream()
                .filter(e -> e.getValue() == l)
                .map(e -> e.getKey())
                .toList();

        // fruit with maximum number of consonants
        Map<String, Long> consonantCount = fruits.stream()
                .collect(Collectors.toMap(Function.identity(), s-> s.chars().filter(c -> "AEIOUaeiou".indexOf(c) == -1).count()));
        long maxConsonants = consonantCount.values().stream().mapToLong(Long::longValue).max().orElse(0);
        List<String> maxConsonantFruits = consonantCount.entrySet().stream()
                .filter(e -> e.getValue() == maxConsonants)
                .map(Map.Entry::getKey)
                .toList();
        println("Fruits with max consonants: " + maxConsonantFruits);

        // check palindrome
        List<String> palindromes = fruits.stream()
                .filter(s -> {
                    String rev = new StringBuilder(s).reverse().toString();
                    return s.equalsIgnoreCase(rev);
                })
                .toList();
        println("Palindromic fruits: " + palindromes);

        //check fruit list is palindromic
        boolean isPalindromicList = IntStream.range(0, fruits.size() / 2)
                .allMatch(i -> fruits.get(i).equalsIgnoreCase(fruits.get(fruits.size() - 1 - i)));
        println("Is fruit list palindromic: " + isPalindromicList);
    }
}

