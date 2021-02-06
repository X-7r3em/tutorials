package asynchronous;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Asynchronous example using Future Task.
 */
public class FutureTaskExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future<Long> futureTask = threadPool.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 1L;
        });

        while (!futureTask.isDone()) {
            System.out.println("FutureTask is not finished yet...");
        }

        long result = futureTask.get();

        System.out.println("The result is: " + result);
        threadPool.shutdown();
    }
}
