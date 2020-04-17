package threadlocal;

import java.util.Arrays;
import java.util.List;

/**
 * Variables local to the Thread are thread safe as they do not share the state with other Threads
 */
public class ThreadList extends Thread {
    
    private final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    
    @Override
    public void run() {
        System.out.println(numbers);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread f = new ThreadList();

        Thread s = new ThreadList();

        f.start();
        s.start();
        f.join();
        s.join();
    }
}