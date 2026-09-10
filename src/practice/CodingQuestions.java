package practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CodingQuestions {
    static void main() {
//        reverseString("Hello World");
//        Map<String, Integer> wordCountMap = countWordsUsingHashMap("This...//?? is a sample sentence with seven words. This sentence is for testing word count.");
//        System.out.println(wordCountMap);
//        iterateMap(wordCountMap);
//        System.out.println("83 is prime? " + isPrime(83));
        System.out.println("10001 is palindrome? " + isPalindrome("10001"));
        System.out.println("10001 num is palindrome? " + isPalindrome(10001));
        printFibonacciSeries(10);
        printFibonacciSeriesRecursive(10, 0, 1);
        printNthFibonacciNumber(10);
    }

    private static void printNthFibonacciNumber(int n) {
        if(n <= 0) {
            System.out.println("Invalid input");
            return;
        }
        int a = 0, b = 1;
        if(n == 1) {
            System.out.println("The " + n + "th Fibonacci number is: " + a);
            return;
        }
        for(int i=2; i<=n; i++) {
            int next = a + b;
            a = b;
            b = next;
        }
        System.out.println("The " + n + "th Fibonacci number is: " + b);
    }

    private static void printFibonacciSeriesRecursive(int n, int a, int b) {
        if(n == 0) return;
        System.out.print(a + " ");
        printFibonacciSeriesRecursive(n-1, b, a+b);
    }

    private static void printFibonacciSeries(int n) {
        int a =0, b = 1;
        System.out.println("Fibonacci Series:");
        for(int i=1; i<=n; i++) {
            System.out.print(a + " ");
            int next = a + b;
            a = b;
            b = next;
        }
    }

    private static boolean isPalindrome(int num) {
        int reversed = 0;
        int original = num;
        int rem;
        while(num>0){
            rem = num % 10;
            reversed = reversed * 10 + rem;
            num = num/10;
        }
        return original == reversed;
    }

    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length()-1;
        while(left<right){
            if(s.charAt(left++) != s.charAt(right--)){
                return false;
            }
        }
        return true;
    }

    private static boolean isPrime(int i) {
        if(i <= 1) return false;
        if(i <= 3) return true;
        if(i % 2 == 0 || i % 3 == 0) return false;
        for(int j=5; j*j<=i; j+=6){
            if(i % j == 0 || i % (j + 2) == 0) return false;
        }
        return true;
    }

    private static void iterateMap(Map<String, Integer> wordCountMap) {
        // using advanced for loop
        for(Map.Entry<String, Integer> entry : wordCountMap.entrySet()){
            System.out.println("Word: " + entry.getKey() + ", Count: " + entry.getValue());
        }
        System.out.println("---------------------");
        // using while loop with iterator
        Iterator<Map.Entry<String, Integer>> iterator = wordCountMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println("Word: " + entry.getKey() + ", Count: " + entry.getValue());
        }
    }

    private static Map<String, Integer> countWordsUsingHashMap(String s) {
        Map<String, Integer> wordCountMap = new HashMap<>();

        String[] words = s.split("\\s+");

        for(String word: words){
            word = word.replaceAll("\\W+", "").toLowerCase();
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        return wordCountMap;
    }

    static void reverseString(String str) {
        char[] charArray = str.toCharArray();
//        for(int i=0; i<str.length(); i++){
//            charArray[i] = str.charAt(i);
//        }
        System.out.println("Char array: " + Arrays.toString(charArray));

//        for(int i=0; i<str.length()/2; i++){
//            char temp = charArray[i];
//            charArray[i] = charArray[str.length() - 1 - i];
//            charArray[str.length() - 1 - i] = temp;
//        }
        int left = 0;
        int right = str.length() - 1;
        while(left < right){
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;
        }

        String reversedStr = new String(charArray);
        System.out.println("Reversed String: " + reversedStr);

    }
}
