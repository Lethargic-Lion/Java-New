package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Demonstrates the use of fair locks in Java
// A fair lock grants access to the longest-waiting thread, ensuring that threads acquire the
// lock in the order they requested it. This can help prevent thread starvation, where a thread waits indefinitely while other threads continuously acquire the lock.
// In this example, we use a ReentrantLock with the fairness parameter set to true. When multiple threads attempt to acquire the lock, they will be granted access in the order they requested it, demonstrating the concept of a fair lock.
// Output will show that threads acquire the lock in the order they were started, rather than one thread acquiring it multiple times while others wait.
// Note: The output may vary based on thread scheduling, but with a fair lock, you should see that threads acquire the lock in the order they were started.
public class UnfairLockExample {
//    private final Lock unfairLock = new ReentrantLock();
    private final Lock fairLock = new ReentrantLock(true);

    public void performTask() {
        fairLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired the lock.");
            // Simulate some work
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + " released the lock.");
            fairLock.unlock();
        }
    }

    static void main() {
        UnfairLockExample example = new UnfairLockExample();

        Runnable task = example::performTask;

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");
        Thread thread3 = new Thread(task, "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
