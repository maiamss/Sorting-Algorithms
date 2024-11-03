package sorting_algorithm;

public class BubbleSort {
    public static SortingMetrics sort(int[] arr) {
        Counter counter = new Counter();
        long start = System.nanoTime();
        
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                counter.incrementComparisons();
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    counter.incrementMovements(3); // Duas movimentações + uma para temp
                }
            }
        }
        
        long end = System.nanoTime();
        return new SortingMetrics((end - start) / 1000000, counter.getComparisons(), counter.getMovements());
    }
}