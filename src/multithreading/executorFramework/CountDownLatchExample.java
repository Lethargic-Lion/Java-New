package multithreading.executorFramework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CountDownLatchExample {
    static void main() {
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        List<Future<String>> futures = List.of(
//                executorService.submit(new DependentService()),
//                executorService.submit(new DependentService()),
//                executorService.submit(new DependentService())
//        );
//        for(Future<String> future : futures){
//            try {
//                System.out.println("Future result: " + future.get());
//            } catch (Exception e) {
//                System.out.println("Exception occurred while getting the result: " + e);
//            }
//        }
//        executorService.shutdown();

        int numberOfTasks = 3; // for which main thread will wait until all the tasks are completed
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // for this we use countdown latch which is a synchronization aid that allows one or more
        // threads to wait until a set of operations being performed in other threads completes.
        // this will help us to make the main thread wait until all the tasks are completed
        // before proceeding with the next steps in the main thread.
        CountDownLatch countDownLatch = new CountDownLatch(numberOfTasks);

        for(int i=0; i<numberOfTasks; i++) {
            executorService.submit(new DependentService(countDownLatch));
        }

        try {
//            countDownLatch.await();
            countDownLatch.await(5, TimeUnit.SECONDS); // this will make the main thread wait for a maximum of 5 seconds for all the tasks to complete, if the tasks are not completed within that time, it will proceed with the next steps in the main thread.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();
        System.out.println("Main thread is proceeding after all tasks are completed.");

    }
}

class DependentService implements Callable<String> {
    private final CountDownLatch countDownLatch;

    public DependentService(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Dependent service is running...");
        try{
            Thread.sleep(2000); // Simulate some work
        } catch(Exception e) {
            System.out.println("Exception occurred: " + e);
        } finally {
            countDownLatch.countDown();
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " has completed its work.");
        return "ok";
    }
}
