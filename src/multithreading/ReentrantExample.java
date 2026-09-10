package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Demonstrates reentrant locks in Java
// A thread can acquire the same lock multiple times without causing a deadlock
// In this example, the outerMethod acquires the lock and then calls innerMethod,
// which also tries to acquire the same lock. Since ReentrantLock allows reentrancy,
// the thread can successfully acquire the lock again.
// If a non-reentrant lock were used, this would result in a deadlock.
// Output:
// Outer method is executing.
// Inner method is executing.

// Reentrancy is also a property of synchronized blocks/methods in Java.
public class ReentrantExample {
    private final Lock lock = new ReentrantLock();

    public void outerMethod() {
        lock.lock();
        try {
            System.out.println("Outer method is executing.");
            innerMethod(); // This will call the inner method which also tries to acquire the same lock
        } finally {
            lock.unlock();
        }
    }

    public void innerMethod() {
        lock.lock();
        try {
            System.out.println("Inner method is executing.");
        } finally {
            lock.unlock();
        }
    }

    static void main() {
        ReentrantExample example = new ReentrantExample();
        example.outerMethod();
    }
}
