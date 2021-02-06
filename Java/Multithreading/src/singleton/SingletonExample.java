package singleton;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This solution solves the problem when the instance is not yet instantiated,
 * but 2 Threads try to access it, and due to null == instance giving true,
 * they both create it.
 */
public class SingletonExample {
    private static SingletonExample instance;
    static SingletonExample getInstance() {
        if (null == instance) {
            Lock lock = new ReentrantLock();
            lock.lock();
            if (null  == instance) {
                instance = new SingletonExample();
            }
            lock.unlock();
        }

        return instance;
    }
}
