import java.util.*;

public class BucketSort {
    public static SortingMetrics sort(int[] arr) {
        Counter counter = new Counter();
        long start = System.nanoTime();
        
        int n = arr.length;
        if (n <= 0) return new SortingMetrics(0, 0, 0);

        List<List<Integer>> buckets = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int num : arr) {
            int bucketIndex = num / 10; // Usando 10 como base
            buckets.get(bucketIndex).add(num);
            counter.incrementMovements(1);
        }

        int index = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int num : bucket) {
                arr[index++] = num;
                counter.incrementMovements(1);
            }
        }
        
        long end = System.nanoTime();
        return new SortingMetrics((end - start) / 1_000_000, counter.getComparisons(), counter.getMovements());
    }
}