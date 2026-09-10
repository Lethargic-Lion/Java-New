package java17;

public class VarUsage {
    static void main() {
        var name = "John Doe";
        var age = 30;
        var isEmployed = true;

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Is Employed: " + isEmployed);

        // var cannot be used for class fields, method parameters, or return types
        // check type of variable at compile time
        // var cannot be used without initialization
        System.out.println(name.getClass().getName());

        // proof that var is not a new type but just a syntactic sugar for type inference
        var anotherName = "Jane Doe";
        System.out.println(anotherName.getClass().getName());

        // proof that var is not dynamically typed
        // var dynamicVar = "I am a string";
        // dynamicVar = 123; // compile-time error
    }
}
