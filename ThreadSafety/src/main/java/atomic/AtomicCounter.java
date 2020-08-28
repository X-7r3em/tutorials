package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic objects do not lock the resource. If two objects want to write on the same object, only one of them will get
 * access to it. The other one will fail and report. After that it can retry or decide on what to do.
 */
public class AtomicCounter {
    
    private final AtomicInteger counter = new AtomicInteger();
    
    public void incrementCounter() {
        counter.incrementAndGet();
    }
    
    public int getCounter() {
        return counter.get();
    }

    public static void main(String[] args) {
        AtomicCounter atomicCounter = new AtomicCounter();
        atomicCounter.incrementCounter();
        System.out.println(atomicCounter.getCounter() == 1);
    }
}