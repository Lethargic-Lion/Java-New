package multithreading.executorFramework;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// CyclicBarrier is a synchronization aid that allows a set of threads to all wait for each other to reach a common barrier point.
// It is useful in situations where you want a group of threads to wait for each other to reach a certain point before proceeding.
// For example, you might have a group of threads that are performing some work in parallel, and you want them to wait for each other
// to finish before moving on to the next step in the process.
// The CyclicBarrier allows you to specify a number of threads that must call the await() method before any of them can proceed.
// Once the specified number of threads have called await(), the barrier is tripped,
// and all waiting threads are released to continue their execution.
// CountDownLatch cannot be reused after the count reaches zero,
// while CyclicBarrier can be reused after the barrier is tripped and the waiting threads are released.
public class CyclicBarrierExample {
    static void main() {
        int numberofTasks = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberofTasks);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(numberofTasks, () -> System.out.println("All tasks have reached the barrier, proceeding to the next step..."));
        executorService.submit(new AnotherDependentService(cyclicBarrier));
        executorService.submit(new AnotherDependentService(cyclicBarrier));
        executorService.submit(new AnotherDependentService(cyclicBarrier));

        cyclicBarrier.reset();
        executorService.shutdown();
        System.out.println("Parties: " + cyclicBarrier.getParties());
        System.out.println("Number of threads currently waiting at the barrier: " + cyclicBarrier.getNumberWaiting());
    }
}

class AnotherDependentService implements Callable<String> {
    private final CyclicBarrier cyclicBarrier;

    public AnotherDependentService(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public String call() {
        System.out.println("Dependent service is running...");
        try {
            Thread.sleep(2000); // Simulating some work
            System.out.println("Dependent service has reached the barrier, waiting for other tasks...");
            cyclicBarrier.await(); // Wait for other tasks to reach the barrier
//            System.out.println("Dependent service is proceeding after the barrier...");
        } catch (Exception e) {
            System.out.println("Exception occurred in DependentService: " + e.getMessage());
        }
        return "Dependent service completed";
    }
}
