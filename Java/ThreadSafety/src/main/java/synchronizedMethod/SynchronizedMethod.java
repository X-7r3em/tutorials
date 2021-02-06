package synchronizedMethod;

/**
 * Synchronized methods allow only one thread to access at a time, so they are thread safe.
 */
public class SynchronizedMethod {
    private static class Counter {
        private int counter = 0;

        public synchronized void incrementCounter() {
            counter += 1;
        }

        public int getCounter() {
            return counter;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread f = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                counter.incrementCounter();
            }
        });

        Thread s = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                counter.incrementCounter();
            }
        });

        f.start();
        s.start();
        f.join();
        s.join();

        System.out.println(counter.getCounter() == 200_000);
    }
}
