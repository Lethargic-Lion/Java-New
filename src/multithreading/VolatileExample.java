package multithreading;

class SharedObject {
    private volatile boolean flag = false;
//    private boolean flag = false;

    public void setFlagTrue() {
        flag = true;
    }

    public void printIfFlagTrue() {
        while(!flag) {
            // Busy-wait until flag becomes true
        }
        System.out.println("Flag is true!");
    }
}


/*
* Here we demonstrate that without the volatile keyword, the reader thread may never see the updated value of flag set by the writer thread,
* leading to an infinite loop.
* By declaring flag as volatile, we ensure that changes made by one thread will be
* visible to other threads, here once the reader thread is stuck in a loop it stays stuck even after the writer thread updates the flag to true,
* because the reader thread may not see the updated value of flag due to caching.
* This can lead to a situation where the reader thread is stuck in an infinite loop,
* waiting for a condition that will never be met.
* By declaring flag as volatile, we ensure that changes made by one thread will be visible to other threads,
* allowing the reader thread to see the updated value of flag and exit the loop when it becomes true.
* Caching is done on a per-thread basis, so each thread may have its own copy of the variable in its cache.
* When one thread updates the variable, it may not immediately update the value in other threads' caches,
* leading to stale data being read by those threads.
* The volatile keyword tells the JVM that the variable can be modified by multiple threads and
* that it should not cache the variable's value. Instead, it should always read the variable's value from main memory,
* ensuring that all threads see the most up-to-date value.
*/
public class VolatileExample {
    static void main() {
        SharedObject sharedObject = new SharedObject();

        Thread writerThread = new Thread(() -> {
            try {
                Thread.sleep(1000); // Simulate some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            sharedObject.setFlagTrue();
            System.out.println("Writer thread set flag to true");
        });

        Thread readerThread = new Thread(() -> {
            try {
                Thread.sleep(200); // Ensure writer thread has time to set the flag
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            sharedObject.printIfFlagTrue();
        });

        writerThread.start();
        readerThread.start();
    }
}
