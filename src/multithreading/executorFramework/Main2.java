package multithreading.executorFramework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main2 {
    static void main() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(() -> {
            System.out.println("Task is running in a separate thread: " + Thread.currentThread().getName());
            return 45;
        });

        if(future.isDone()){
            System.out.println("Task is completed");
        }

        System.out.println("Future result: " + future.get());

        // isDone method can be used to check if the task is completed or not
        if(future.isDone()){
            System.out.println("Task is completed");
        } else {
            System.out.println("Task is not completed yet");
        }
//        executorService.shutdown();

        // Difference between Callable and Runnable:
        // 1. Callable can return a result and can throw a checked exception, while Runnable cannot return a result and cannot throw a checked exception.
        // 2. Both are functional interfaces and can be used with lambda expressions.
        // Example of Callable:
        Callable<Integer> callableTask = () -> {
            System.out.println("Callable task is running in a separate thread: " + Thread.currentThread().getName());
            return 45;
        };
        Future<Integer> callableFuture = executorService.submit(callableTask);
        System.out.println("Callable Future result: " + callableFuture.get());

        // Example of Runnable:
        Runnable runnableTask = () -> {
            System.out.println("Runnable task is running in a separate thread: " + Thread.currentThread().getName());
        };
        Future<?> runnableFuture = executorService.submit(runnableTask);
        runnableFuture.get(); // This will block until the Runnable task is completed

        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        Callable<Integer> callable = () -> {
            System.out.println("Callable task is running in a separate thread: " + Thread.currentThread().getName());
            return 45;
        };
        Callable<Integer> callable1 = () -> {
            System.out.println("Callable1 task is running in a separate thread: " + Thread.currentThread().getName());
            return 46;
        };
        Callable<Integer> callable2 = () -> {
            System.out.println("Callable2 task is running in a separate thread: " + Thread.currentThread().getName());
            return 47;
        };
        List<Callable<Integer>> list = Arrays.asList(callable, callable1, callable2);
//        List<Future<Integer>> futures = executorService1.invokeAll(list);

        // invokeAll method with timeout will block until all tasks have completed execution or
        // the timeout occurs, whichever happens first. If the timeout occurs before all tasks have completed execution,
        // it will return a list of Future objects representing the results of the tasks that have completed execution before the timeout,
        // and the Future objects for the tasks that
        // have not completed execution will be cancelled.
        List<Future<Integer>> futures = executorService1.invokeAll(list, 1, TimeUnit.SECONDS);

        executorService1.shutdown();
        // invokeAll method will block until all tasks have completed execution and will return a list of Future objects representing the results of the tasks.
        System.out.println("All tasks have completed execution");
        for (Future<Integer> f : futures) {
            System.out.println("Future result: " + f.get());
        }

        // invokeAny method will block until at least one of the tasks has completed execution and will return the result of that task. If the timeout occurs before any task has completed execution, it will throw a TimeoutException.
        // invokeAny with timeout will block until at least one of the tasks has completed execution or the timeout occurs, whichever happens first. If the timeout occurs before any task has completed execution, it will throw a TimeoutException.
    }
}
