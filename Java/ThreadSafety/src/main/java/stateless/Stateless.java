package stateless;

/**
 * Stateless example of thread safety. There are no shared resources, so the result is always deterministic.
 */
public class Stateless {

    public static int sum(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread f = new Thread(() -> System.out.println(sum(10, 5) == 15));

        Thread s = new Thread(() -> System.out.println(sum(10, 20) == 30));

        f.start();
        s.start();
        f.join();
        s.join();
    }
}

