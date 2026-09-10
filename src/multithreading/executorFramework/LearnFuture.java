package multithreading.executorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class LearnFuture {
    static void main() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Task was interrupted: " + e);
            }
            System.out.println("Task is running in a separate thread: " + Thread.currentThread().getName());
            return 10;
        });

//        Integer i = null;
//        try {
//            System.out.println("Task completed: " + future.isDone());
//
//            // This will block until the task is completed and return the result
//            // i = future.get();
//
//            // This will block for a maximum of 1 second and return the result if the task is completed within that time, otherwise it will throw a TimeoutException
//            i = future.get(1, TimeUnit.SECONDS);
//
//            System.out.println("Future result: " + i);
//            System.out.println("Task is completed: " + future.isDone());
//        } catch (Exception e) {
//            System.out.println("Exception occurred while getting the result: " + e);
//        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted: " + e);
        }
        future.cancel(false);
        System.out.println("Task cancelled: " + future.isCancelled());
        System.out.println("Task completed: " + future.isDone());
        executorService.shutdown();
    }
}
