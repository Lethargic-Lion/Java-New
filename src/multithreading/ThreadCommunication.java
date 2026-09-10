package multithreading;

class SharedResource {
    private int value;

    private boolean hasData;

    public synchronized void produce(int newValue) {
        while(hasData) {
            try {
                wait(); // Wait until the consumer consumes the value
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        value = newValue;
        hasData = true; // Indicate that a value has been produced
        System.out.println("Produced: " + value);
        notify(); // Notify waiting consumer
    }

    public synchronized void consume() {
        while (!hasData) {
            try {
                wait(); // Wait until a value is produced
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Consumed: " + value);
        hasData = false; // Reset for next production
        notify();
    }
}

class Producer implements Runnable {
    private final SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            resource.produce(i);
            try {
                Thread.sleep(100); // Simulate time taken to produce
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable {
    private final SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            resource.consume();
            try {
                Thread.sleep(150); // Simulate time taken to consume
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class ThreadCommunication {
    static void main() {
        SharedResource resource = new SharedResource();
        Thread producerThread = new Thread(new Producer(resource));
        Thread consumerThread = new Thread(new Consumer(resource));

        producerThread.start();
        consumerThread.start();
    }
}
