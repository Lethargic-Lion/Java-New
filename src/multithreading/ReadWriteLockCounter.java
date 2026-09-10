package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCounter {
    private int count = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void increment() {
        writeLock.lock();
        try {
            count++;
            System.out.println("Thread : "+ Thread.currentThread().getName() + "Count incremented: " + count);
        } finally {
            writeLock.unlock();
        }
    }

    public int getCount() {
        readLock.lock();
        try {
            System.out.println("Thread : "+ Thread.currentThread().getName() + "Count read: " + count);
            return count;
        } finally {
            readLock.unlock();
        }
    }

    static void main() {
        ReadWriteLockCounter counter = new ReadWriteLockCounter();

        Runnable incrementTask = new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<5; i++) {
                    counter.increment();
                    try {
                        Thread.sleep(50); // Simulate some delay
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };

        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<5; i++) {
                    counter.getCount();
//                    try {
//                        Thread.sleep(150); // Simulate some delay
//                    } catch (InterruptedException e) {
//                        Thread.currentThread().interrupt();
//                    }
                }
            }
        };

        Thread thread1 = new Thread(incrementTask, "Increment-Thread-1");
        Thread thread2 = new Thread(readTask, "Read-Thread-2");
        Thread thread3 = new Thread(readTask, "Read-Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();

        try{
            thread1.join();
            thread2.join();
            thread3.join();
        } catch(InterruptedException e){
            System.out.println(e);
        }
        System.out.println("Main thread finished execution.");
    }
}
