package synchronizedCollections;

import java.util.*;

/**
 * Synchronized collections give us thread safety by internally locking the collection and making it accessible
 * to only one thread at a time
 */
public class SynchronizedCollections {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> syncCollection = Collections.synchronizedList(new ArrayList<>());
        Thread thread1 = new Thread(() -> syncCollection.addAll(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Thread thread2 = new Thread(() -> syncCollection.addAll(Arrays.asList(7, 8, 9, 10, 11, 12)));
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        boolean isValid = true;
        for (int i = 1; i <= 12; i++) {
            if (!(syncCollection.get(i - 1) == i)) {
                isValid = false;
                break;
            }
        }

        System.out.println(isValid);
    }
}
