package multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/*
    * CompletableFuture is a powerful class in Java that allows you to write asynchronous, non-blocking code. It provides a way to handle the result of an asynchronous computation and allows you to chain multiple computations together.
    * Introduced in Java 8, CompletableFuture is part of the java.util.concurrent package and is designed to work with the ForkJoinPool.commonPool() by default, but you can also specify your own Executor for more control over thread management.
    * Key features of CompletableFuture:
    * 1. Asynchronous Computation: You can run tasks asynchronously without blocking the main thread.
    * 2. Chaining: You can chain multiple CompletableFutures together, allowing you to perform a sequence of operations.
    * 3. Exception Handling: CompletableFuture provides methods to handle exceptions that may occur during asynchronous computations.
    * 4. Combining Results: You can combine the results of multiple CompletableFutures using methods like thenCombine and thenAcceptBoth.
    * 5. Timeouts: You can specify timeouts for asynchronous operations using the orTimeout method.
    *
    * Example usage:
    *
    * CompletableFuture.supplyAsync(() -> {
    *     // Simulate a long-running task
    *     return "Hello, World!";
    * }).thenAccept(result -> {
    *     System.out.println("Result: " + result);
    * }).exceptionally(ex -> {
    *     System.err.println("Error: " + ex.getMessage());
    *     return null;
    * });
* */
public class LearnCompletableFuture {
    static void main() throws ExecutionException, InterruptedException {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Worker thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                System.out.println("Task was interrupted: " + e);
            }
            System.out.println("Task is running in a separate thread: " + Thread.currentThread().getName());
            return "ok";
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Worker thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                System.out.println("Task was interrupted: " + e);
            }
            System.out.println("Task is running in a separate thread: " + Thread.currentThread().getName());
            return "ok";
        });
        CompletableFuture<Void> f = CompletableFuture.allOf(f1, f2);

        f.get();

        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Worker thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                System.out.println("Task was interrupted: " + e);
            }
            System.out.println("Task is running in a separate thread: " + Thread.currentThread().getName());
            return "ok";
        });

        System.out.println("Main thread: " + Thread.currentThread().getName());
    }
}
