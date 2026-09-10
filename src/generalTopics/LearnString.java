package generalTopics;

public class LearnString {
    static void main() {
        String a = "Hello";
        a.concat(" World");
        System.out.println(a); // Output: Hello
        a = a.concat(" World");
        System.out.println(a); // Output: Hello World

        // Runtime concatenation
        String b = "b";
        String c = "c";
        String d = b + c; // This will create a new string "bc" at runtime
        System.out.println(d); // Output: bc
        // here b and c are variables, so the concatenation happens at runtime, and a new string "bc" is created in the heap memory not in the string pool,
        // so d will reference the new string "bc" in the heap memory.

        // using intern() method to add the string to the string pool
        String e = d.intern(); // This will add the string "bc" to the

        // StringBuilder
        StringBuilder stringBuilder = new StringBuilder("Hello");
        StringBuilder stringBuilder2 = new StringBuilder("Hello");
        System.out.println(stringBuilder2 == stringBuilder);
        System.out.println(stringBuilder2.equals(stringBuilder));

        // Equals method in StringBuilder class is not overridden,
        // so it will compare the reference of the objects,
        // not the content of the objects, so it will return false.

        // To compare the content of the StringBuilder objects,
        // we can use the toString() method to convert the
        // StringBuilder objects to String objects and
        // then compare them using the equals() method of the String class.
        // or we can use the contentEquals() method of the StringBuilder class
        // to compare the content of the StringBuilder objects directly.
        System.out.println(stringBuilder2.toString().equals(stringBuilder.toString()));
        System.out.println(stringBuilder2.compareTo(stringBuilder) == 0);
    }
}
