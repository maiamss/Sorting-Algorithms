import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingTest {
    public static void main(String[] args) {
    
        String[] fileNames = {
            "../tests/1000_numbers.txt",
            "../tests/5000_numbers.txt",
            "../tests/10000_numbers.txt"
        };
        
        // Nomes simplificados para os testes
        String[] testNames = {
            "Teste 1000 Números",
            "Teste 5000 Números",
            "Teste 10000 Números"
        };

        for (int i = 0; i < fileNames.length; i++) {
            String fileName = fileNames[i];
            int[] data = readDataFromFile(fileName);

        

            // Resultados dos testes
            System.out.println(testNames[i]);
            System.out.println("------------------------------");

            // Teste Bubble Sort
            SortingMetrics bubbleMetrics = BubbleSort.sort(Arrays.copyOf(data, data.length)); // Copia o array
            System.out.println("Bubble Sort: " + bubbleMetrics.getRuntime() + " ms, Comparações: " + bubbleMetrics.getComparisons() + ", Movimentações: " + bubbleMetrics.getMovements());

            // Teste Binary Insertion Sort
            SortingMetrics binaryInsertionMetrics = BinaryInsertionSort.sort(Arrays.copyOf(data, data.length)); // Copia o array
            System.out.println("Binary Insertion Sort: " + binaryInsertionMetrics.getRuntime() + " ms, Comparações: " + binaryInsertionMetrics.getComparisons() + ", Movimentações: " + binaryInsertionMetrics.getMovements());

            // Teste Bucket Sort
            SortingMetrics bucketMetrics = BucketSort.sort(Arrays.copyOf(data, data.length)); // Copia o array
            System.out.println("Bucket Sort: " + bucketMetrics.getRuntime() + " ms, Comparações: " + bucketMetrics.getComparisons() + ", Movimentações: " + bucketMetrics.getMovements());

            // Quebra de linha para separar os testes
            System.out.println();
        }
    }

    public static int[] readDataFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            List<Integer> numbers = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
            return numbers.stream().mapToInt(i -> i).toArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new int[0]; // Retorna um array vazio em caso de erro
        }
    }
}
