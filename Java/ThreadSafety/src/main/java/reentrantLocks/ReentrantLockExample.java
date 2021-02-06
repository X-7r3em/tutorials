package reentrantLocks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * With {@link ReentrantLock} we can lock a block of code with the the lock object. This guarantees the Thread safety.
 */
public class ReentrantLockExample {
    public static class ReentrantLockCounter {

        private int counter;

        // The fair: True assures that the lock is given to the longest waiting thread
        private final ReentrantLock reLock = new ReentrantLock(true);

        public void incrementCounter() {
            reLock.lock();
            try {
                counter += 1;
            } finally {
                reLock.unlock();
            }
        }

        public int getCounter() {
            return counter;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockCounter counter = new ReentrantLockCounter();
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
