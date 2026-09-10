package multithreading.executorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
* Executor Framework is a high-level API for managing threads in Java.
* It provides a way to decouple task submission from the mechanics of how each task will be run,
* including thread creation, scheduling, and management.
* The Executor Framework includes several key components:
* 1. Executor: The main interface for executing tasks.
*    It has a single method, execute(Runnable command), which accepts a Runnable task for execution.
* 2. ExecutorService: A subinterface of Executor that provides additional methods for managing the lifecycle of tasks and the executor itself.
*    It includes methods for submitting tasks, shutting down the executor, and waiting for task completion.
* 3. ThreadPoolExecutor: A concrete implementation of ExecutorService that uses a pool of threads to execute tasks.
*    It allows for efficient management of a large number of tasks by reusing threads and controlling the number of concurrent threads.
* 4. ScheduledExecutorService: An extension of ExecutorService that supports scheduling tasks to run after a delay or periodically.
* 5. Executors: A utility class that provides factory methods for creating different types of ExecutorService instances, such as fixed thread pools, cached thread pools, and single-thread executors.
* The Executor Framework simplifies concurrent programming by abstracting away the complexities of thread management and providing a more flexible and powerful way to execute tasks asynchronously.
* */
public class Main {
//    static void main() {
//        System.out.println("Executor Framework example");
//        long startTime = System.currentTimeMillis();
//
//        Thread[] threads = new Thread[5];
//        for (int i=0; i<5; i++) {
//            int finalI = i;
//            threads[i] = new Thread(() -> {
//                long result = factorial(finalI);
//                System.out.println("Factorial of " + finalI + " calculated: " + result);
//            });
//            threads[i].start();
//        }
//
//        for(Thread thread : threads) {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }
//
//        System.out.println("Time taken: " + (System.currentTimeMillis() - startTime) + " ms");
//    }

    static void main() {
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i=0; i<9; i++) {
            int finalI = i;
            executorService.submit(() -> {
                long result = factorial(finalI);
                System.out.println("Factorial of " + finalI + " calculated: " + result);
            });
        }

        // shutdown method will not accept new tasks but will continue to execute already submitted tasks
        executorService.shutdown();
        System.out.println(executorService.isTerminated());


        // awaitTermination method can be used to block until all tasks have completed execution after a shutdown request, or the timeout occurs, or the current thread is interrupted, whichever happens first.
        try {
            executorService.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Time taken: " + (System.currentTimeMillis() - startTime) + " ms");

        // isShutdown method can be used to check if the executor service has been shut down or not
        System.out.println(executorService.isShutdown());

        // isTerminated method can be used to check if all tasks have completed execution after a shutdown request or not
        // Note: isTerminated will return true only if the executor service has been shut down and all tasks have completed execution.
        // If the executor service is still running or if there are still tasks that have not completed execution, it will return false.
        System.out.println(executorService.isTerminated());


    }

    private static long factorial(int n) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
