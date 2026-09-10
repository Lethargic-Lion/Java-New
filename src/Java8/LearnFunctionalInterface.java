package Java8;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@FunctionalInterface
interface PaymentStrategy2 {
    void pay();
}

public class LearnFunctionalInterface {
    static void main() {
        PaymentStrategy2 paymentStrategy = () -> {
            System.out.println("Payment done using functional interface");
        };
        paymentStrategy.pay();

        Function<Integer, Integer> squareFunction = (x) -> x * x;
        System.out.println("Square of 5 is: " + squareFunction.apply(5));

        Predicate<String> isEmptyPredicate = (s) -> s.isEmpty();
        System.out.println("Is empty string: " + isEmptyPredicate.test(""));

        Supplier<String> stringSupplier = () -> "Hello, World!";
        System.out.println("Supplier provides: " + stringSupplier.get());

        Consumer<String> stringConsumer = (s) -> System.out.println("Consumed: " + s);
        stringConsumer.accept("Functional Interfaces in Java");

        Runnable task = () -> System.out.println("Runnable in Java");
        task.run();

        Callable<String> callableTask = () -> "Callable in Java";
        try {
            System.out.println("Callable returns: " + callableTask.call());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
