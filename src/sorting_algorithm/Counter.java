package sorting_algorithm;

public class Counter {
    private int comparisons = 0;
    private int movements = 0;

    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementMovements(int count) {
        movements += count;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getMovements() {
        return movements;
    }
}