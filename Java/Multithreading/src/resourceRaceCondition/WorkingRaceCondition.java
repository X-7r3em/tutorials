package resourceRaceCondition;

import java.util.concurrent.locks.ReentrantLock;

/**
 * The external resource is locked and only one Thread can work wit it. The
 * result is that there is no data loss.
 */
public class WorkingRaceCondition {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        final int[] money = {0};
        Thread a = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                lock.lock();
                money[0]++;
                lock.unlock();
            }
        });

        Thread b = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                lock.lock();
                money[0]++;
                lock.unlock();
            }
        });

        a.start();
        b.start();

        a.join();
        b.join();

        System.out.println(money[0]);
    }
}
