public class BinaryInsertionSort {
    public static SortingMetrics sort(int[] arr) {
        Counter counter = new Counter();
        long start = System.nanoTime();
        
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            int pos = binarySearch(arr, key, 0, j, counter);
            while (j >= pos) {
                arr[j + 1] = arr[j];
                counter.incrementMovements(1);
                j--;
            }
            arr[j + 1] = key;
            counter.incrementMovements(1);
        }
        
        long end = System.nanoTime();
        return new SortingMetrics((end - start) / 1_000_000, counter.getComparisons(), counter.getMovements());
    }

    private static int binarySearch(int[] arr, int key, int low, int high, Counter counter) {
        while (low <= high) {
            counter.incrementComparisons();
            int mid = low + (high - low) / 2;
            if (key < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}