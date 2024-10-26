public class SortingMetrics {
    private long runtime;
    private int comparisons;
    private int movements;

    public SortingMetrics(long runtime, int comparisons, int movements) {
        this.runtime = runtime;
        this.comparisons = comparisons;
        this.movements = movements;
    }

    public long getRuntime() {
        return runtime;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getMovements() {
        return movements;
    }
}