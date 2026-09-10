package multithreading.executorFramework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LearnScheduledExecutorService {
    static void main() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.schedule(() -> System.out.println("Task executed after 2 seconds delay"), 2, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("Task executed at fixed rate of 3 seconds"), 3, 3, TimeUnit.SECONDS);

        // The difference between scheduleAtFixedRate and scheduleWithFixedDelay is that scheduleAtFixedRate will execute the task at a fixed rate,
        // meaning that it will try to execute the task every 3 seconds regardless of how long the task takes to execute.
        // On the other hand, scheduleWithFixedDelay will execute the task with a fixed delay,
        // meaning that it will wait for 3 seconds after the previous execution of the task is completed before executing the task again.
        scheduledExecutorService.scheduleWithFixedDelay(() -> System.out.println("Task executed at fixed rate of 3 seconds"), 3, 3, TimeUnit.SECONDS);

        scheduledExecutorService.schedule(() -> {
            System.out.println("Initializing shutdown...");
            scheduledExecutorService.shutdown();
            System.out.println("Is scheduledExecutorService shutdown? " + scheduledExecutorService.isShutdown());
        }, 20, TimeUnit.SECONDS);
    }
}
