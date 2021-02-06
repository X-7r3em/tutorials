import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompletableFutureExampleTest {

    @Test
    public void givenACompletedFuture_whenGet_thenReturnResult() throws ExecutionException, InterruptedException {
        //Creates a completed future
        Future<String> completableFuture =
                CompletableFuture.completedFuture("Hello");

        //Completable future get is blocking
        String result = completableFuture.get();
        assertEquals("Hello", result);
    }

    @Test
    public void simpleSompletableFutureAsynchronousExample() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello async");
            return "Hello";
        });

        Thread.sleep(1000);
        assertEquals("Hello", future.get());
    }

    @Test
    public void chainingCompletableFutureResults() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello async");
            return "Hello";
        });

        CompletableFuture<String> nextFuture = future.thenApply((res) -> {
            System.out.println("Hello Chained Async");
            return res + " World";
        });

        Thread.sleep(1000);
        assertEquals("Hello World", nextFuture.get());
    }

    @Test
    public void completableFutureWithConsumer() throws InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello async");
            return "Hello";
        });

        future.thenAccept((res) -> System.out.println(res + " Consumer Async"));

        Thread.sleep(1000);
    }

    @Test
    public void completableFutureWithRun() throws InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello async");
            return "Hello";
        });

        future.thenRun(() -> System.out.println("Run Async"));

        Thread.sleep(1000);
    }

    @Test
    public void completableFutureWithThenCompose() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello")
                // Returns a completion stage instead of the result itself
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));

        assertEquals("Hello World", completableFuture.get());
    }

    //Combining two futures together
    @Test
    public void completableFutureCombining() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombine(CompletableFuture.supplyAsync(
                        () -> " World"), (s1, s2) -> s1 + s2);

        assertEquals("Hello World", completableFuture.get());
    }

    @Test
    public void completableFutureHandlingExceptions() throws InterruptedException, ExecutionException {
        String name = null;

        // If an exception is thrown in any of the futures, the Handle method catches it
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        })
                .handle((s, t) -> s != null ? s : "Hello, Stranger! " + t.getLocalizedMessage());

        assertEquals("Hello, Stranger! java.lang.RuntimeException: Computation error!", completableFuture.get());
    }

}
