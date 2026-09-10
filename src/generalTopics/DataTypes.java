package generalTopics;

class A {
    int myInt = 5;
    void print() {
        System.out.println("This is class A");
    }
}

class B extends A {

    @Override
    void print() {
        System.out.println("This is class B");
    }

    static void main() {
        B b = new B();
        b.print(); // This will call the overridden method in class B

        A a = new B();
        a.print(); // This will also call the overridden method in class B due to polymorphism

        // type casting of a B object to an A reference
        A a2 = (A) b; // This is valid because B is a subclass of A
        a2.print(); // This will call the overridden method in class B due to polymorphism
    }
}

public class DataTypes {
    static void main() {
        // Primitive data types
        int myInt = 10; // Integer
        double myDouble = 3.14; // Floating-point number
        char myChar = 'A'; // Character
        boolean myBoolean = true; // Boolean

        // Non-primitive data types
        String myString = "Hello, World!"; // String

        // Output the values
        System.out.println("Integer: " + myInt);
        System.out.println("Double: " + myDouble);
        System.out.println("Character: " + myChar);
        System.out.println("Boolean: " + myBoolean);
        System.out.println("String: " + myString);

        // Type casting
        int myIntFromDouble = (int) myDouble; // Explicit casting
        System.out.println("Integer from double: " + myIntFromDouble);

        // Wrapper classes for primitive types
        Integer myIntegerObject = myInt; // Autoboxing
        Double myDoubleObject = myDouble; // Autoboxing
        System.out.println("Integer object: " + myIntegerObject);
        System.out.println("Double object: " + myDoubleObject);

        // Casting between wrapper classes
        Double myDoubleFromIntegerObject = myIntegerObject.doubleValue(); // Using method to convert
        System.out.println("Double from Integer object: " + myDoubleFromIntegerObject);

    }
}
