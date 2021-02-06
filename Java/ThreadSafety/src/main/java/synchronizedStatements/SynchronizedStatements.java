package synchronizedStatements;

/**
 * Synchronized statements make the statement accessible for only one thread and thus making it thread safe. We
 * need to give the intrinsic lock reference.
 */
public class SynchronizedStatements {
    public static class ObjectLockCounter {

        private int counter = 0;
        // It is a good practice for the lock to not be this as it can be obtained more easily than an internal object
        // and a DoS attack can be performed
        private final Object lock = new Object();

        public void incrementCounter() {
            synchronized(lock) {
                counter += 1;
            }
        }

        public int getCounter() {
            return counter;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ObjectLockCounter counter = new ObjectLockCounter();
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
