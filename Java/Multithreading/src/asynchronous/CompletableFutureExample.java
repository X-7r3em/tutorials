package asynchronous;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Asynchronous example using Completable Future.
 */
public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Long> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 100L;
        });

//        while (!completableFuture.isDone()) {
//            System.out.println("CompletableFuture is not finished yet...");
//        }

        System.out.println("After cycle.");

        long result = completableFuture.get();

        System.out.println("The result is: " + result);
    }
}
