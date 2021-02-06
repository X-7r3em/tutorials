package concurrentCollections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Concurrent collections achieve thread-safety by dividing their data into segments. In a ConcurrentHashMap,
 * for instance, several threads can acquire locks on different map segments, so multiple threads can access
 * the Map at the same time.
 * Concurrent collections are much more performant than synchronized collections, due to the inherent advantages
 * of concurrent thread access.
 */
public class ConcurrentCollections {
    public static void main(String[] args) throws InterruptedException {
        Map<String,String> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("1", "one");
        concurrentMap.put("2", "two");
        concurrentMap.put("3", "three");

        System.out.println(concurrentMap);
    }
}
