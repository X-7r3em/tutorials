package resourceRaceCondition;

/**
 * Shows what a race condition for resources is. The money will not be calculated
 * properly, as it is an external resource and both Threads have access to it.
 * Both are editing it at the same moment and the result is not correct.
 */
public class RaceCondition {
    public static void main(String[] args) throws InterruptedException {
        final int[] money = {0};
        Thread a = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                money[0]++;
            }
        });

        Thread b = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                money[0]++;
            }
        });

        a.start();
        b.start();

        a.join();
        b.join();

        System.out.println(money[0]);
    }
}
