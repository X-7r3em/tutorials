package resourceRaceCondition;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Shows what a deadlock is. LockA is being used by Thread A and LockB is being used by Thread B.
 * For Thread A to proceed it needs access to lockB, but it is a locked in Thread B.
 * For Thread B to proceed it needs access to lockA, but it is a locked in Thread A.
 * Thus the program can not continue.
 */
public class DeadLock {
    public static void main(String[] args) throws InterruptedException {
        final int[] money = {0};
        ReentrantLock lockA = new ReentrantLock();
        ReentrantLock lockB = new ReentrantLock();
        Thread A = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                lockA.lock();
                try {
                    Thread.sleep(1000);
                    lockB.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                money[0]++;
                lockA.unlock();
            }
        });

        Thread B = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                lockB.lock();
                try {
                    Thread.sleep(1000);
                    lockA.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                money[0]++;
                lockB.unlock();
            }
        });

        A.start();
        B.start();

        A.join();
        B.join();

        System.out.println(money[0]);
    }
}
