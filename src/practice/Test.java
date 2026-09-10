package practice;

class A {
    class B {
        class C {
            class D {
                void display() {
                    System.out.println("Inside class D");
                }
            }
        }
    }

    static void main() {
        A a = new A();
        A.B b = a.new B();
        A.B.C c = b.new C();
        A.B.C.D d = c.new D();
        d.display();
    }
}


public class Test {
    static void main() throws InterruptedException {
        // handle multiple exceptions in a single catch block
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]); // This will throw ArrayIndexOutOfBoundsException
            String str = null;
            System.out.println(str.length()); // This will throw NullPointerException
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("An exception occurred: " + e.getMessage());
        } finally {
            System.out.println("Execution completed.");
        }

        // common exceptions examples
//        try {
//            int result = 10 / 0; // ArithmeticException
//        } catch (ArithmeticException e) {
//            System.out.println("Arithmetic Exception: " + e.getMessage());
//        }
//
//        try {
//            String s = "abc";
//            int num = Integer.parseInt(s); // NumberFormatException
//        } catch (NumberFormatException e) {
//            System.out.println("Number Format Exception: " + e.getMessage());
//        }
//
//        try {
//            Object x = new Integer(0);
//            System.out.println((String)x); // ClassCastException
//        } catch (ClassCastException e) {
//            System.out.println("Class Cast Exception: " + e.getMessage());
//        }
//
//        try {
//            String str = null;
//            System.out.println(str.length()); // NullPointerException
//        } catch (NullPointerException e) {
//            System.out.println("Null Pointer Exception: " + e.getMessage());
//        }
//
//        try {
//            int[] arr = new int[5];
//            System.out.println(arr[10]); // ArrayIndexOutOfBoundsException
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("Array Index Out Of Bounds Exception: " + e.getMessage());
//        }
//
//        try {
//            java.io.FileReader file = new java.io.FileReader("non_existent_file.txt"); // FileNotFoundException
//        } catch (java.io.FileNotFoundException e) {
//            System.out.println("File Not Found Exception: " + e.getMessage());
//        }

        //  Autoboxing and Unboxing example
        Integer boxedInt = 42; // Autoboxing
        int unboxedInt = boxedInt; // Unboxing
        System.out.println("Boxed Integer: " + boxedInt);
        System.out.println("Unboxed Integer: " + unboxedInt);
        syncDemo();
    }

    // String vs StringBuilder vs StringBuffer
    static void stringExamples() {
        // String (immutable)
        String str1 = "Hello";
        str1 += " World"; // Creates a new String object
        System.out.println("String: " + str1);

        // StringBuilder (mutable, not synchronized)
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World"); // Modifies the existing object
        System.out.println("StringBuilder: " + sb.toString());

        // StringBuffer (mutable, synchronized)
        StringBuffer sbuf = new StringBuffer("Hello");
        sbuf.append(" World"); // Modifies the existing object
        System.out.println("StringBuffer: " + sbuf.toString());
    }

    static void syncDemo() throws InterruptedException {
        StringBuffer sbuf = new StringBuffer("Start");
        StringBuilder sbld = new StringBuilder("Start");

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                sbuf.append("x");   // thread-safe (synchronized method)
                sbld.append("x");   // NOT thread-safe
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("StringBuffer length:  " + sbuf.length()); // should be Start(5)+2000 = 2005
        System.out.println("StringBuilder length: " + sbld.length()); // often < 2005 or may even throw
        System.out.println("StringBuffer content: " + sbuf.toString());
        System.out.println("StringBuilder content: " + sbld.toString());
    }

}
