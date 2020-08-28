package readWriteLocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * With {@link ReentrantReadWriteLock} we can lock a block of code with the the lock object. A ReadWriteLock lock
 * actually uses a pair of associated locks, one for read-only operations and other for writing operations.
 * As a result, it's possible to have many threads reading a resource, as long as there's no thread writing
 * to it. Moreover, the thread writing to the resource will prevent other threads from reading it.
 */
public class ReadWriteLockExample {
    public static class ReentrantReadWriteLockCounter {

        private int counter;
        private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        private final Lock readLock = rwLock.readLock();
        private final Lock writeLock = rwLock.writeLock();

        public void incrementCounter() {
            writeLock.lock();
            try {
                counter += 1;
            } finally {
                writeLock.unlock();
            }
        }

        public int getCounter() {
            readLock.lock();
            try {
                return counter;
            } finally {
                readLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWriteLockCounter counter = new ReentrantReadWriteLockCounter();
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
