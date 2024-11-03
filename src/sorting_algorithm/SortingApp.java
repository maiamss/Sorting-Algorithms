package sorting_algorithm;

//imports da interface
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos; 
import javafx.beans.property.SimpleStringProperty; 
import javafx.beans.property.SimpleLongProperty; 
import javafx.beans.property.SimpleIntegerProperty; 


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SortingApp extends Application {

    private Stage primaryStage;
    private Scene selectionScene, sortingScene;
    private String selectedFileName;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Sorting Algorithm Tester");

        //tela 1
        VBox selectionLayout = new VBox(10);
        selectionLayout.getStyleClass().add("vbox");

        ComboBox<String> numberChoice = new ComboBox<>();
        numberChoice.getItems().addAll("1000", "5000", "10000");
        numberChoice.setPromptText("Escolha o número de entradas");

        Button nextButton = new Button("Próximo");
        nextButton.setOnAction(e -> {
            selectedFileName = "../tests/" + numberChoice.getValue() + "_numbers.txt";
            showSortingScene();
        });

        selectionLayout.getChildren().addAll(numberChoice, nextButton);
        selectionLayout.setAlignment(Pos.CENTER); // Centralizando
        selectionScene = new Scene(selectionLayout, 300, 150);
        selectionScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        //tela 2
        VBox sortingLayout = new VBox(10);
        sortingLayout.getStyleClass().add("vbox");

        CheckBox bubbleSortCheckBox = new CheckBox("Bubble Sort");
        CheckBox binaryInsertionSortCheckBox = new CheckBox("Binary Insertion Sort");
        CheckBox bucketSortCheckBox = new CheckBox("Bucket Sort");

        Button runButton = new Button("Executar");
        runButton.setOnAction(e -> {
            List<String> selectedMethods = new ArrayList<>();
            if (bubbleSortCheckBox.isSelected()) selectedMethods.add("Bubble Sort");
            if (binaryInsertionSortCheckBox.isSelected()) selectedMethods.add("Binary Insertion Sort");
            if (bucketSortCheckBox.isSelected()) selectedMethods.add("Bucket Sort");

            runSortingAlgorithms(selectedMethods);
        });

        sortingLayout.getChildren().addAll(bubbleSortCheckBox, binaryInsertionSortCheckBox, bucketSortCheckBox, runButton);
        sortingLayout.setAlignment(Pos.CENTER); // Centralizando
        sortingScene = new Scene(sortingLayout, 300, 250);
        sortingScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(selectionScene);
        primaryStage.show();
    }

    private void showSortingScene() {
        primaryStage.setScene(sortingScene);
    }

    private void runSortingAlgorithms(List<String> selectedMethods) {
        List<SortingResult> results = new ArrayList<>();

        for (String method : selectedMethods) {
            SortingMetrics metrics = runSortingMethod(method);
            results.add(new SortingResult(method, metrics.getRuntime(), metrics.getComparisons(), metrics.getMovements()));
        }

        showResultsTable(results);
    }

    private SortingMetrics runSortingMethod(String method) {
        int[] data = readDataFromFile(selectedFileName);
        SortingMetrics metrics = null;

        switch (method) {
            case "Bubble Sort":
                metrics = BubbleSort.sort(data);
                break;
            case "Binary Insertion Sort":
                metrics = BinaryInsertionSort.sort(data);
                break;
            case "Bucket Sort":
                metrics = BucketSort.sort(data);
                break;
        }

        return metrics;
    }

    private int[] readDataFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            List<Integer> numbers = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
            return numbers.stream().mapToInt(i -> i).toArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new int[0];
        }
    }

    private void showResultsTable(List<SortingResult> results) {
        Stage resultsStage = new Stage();
        resultsStage.setTitle("Resultados");

        TableView<SortingResult> tableView = new TableView<>();
        TableColumn<SortingResult, String> methodColumn = new TableColumn<>("Método");
        methodColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().method));

        TableColumn<SortingResult, Long> timeColumn = new TableColumn<>("Tempo (ms)");
        timeColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().time).asObject());

        TableColumn<SortingResult, Integer> comparisonsColumn = new TableColumn<>("Comparações");
        comparisonsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().comparisons).asObject());

        TableColumn<SortingResult, Integer> movementsColumn = new TableColumn<>("Movimentações");
        movementsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().movements).asObject());

        tableView.getColumns().addAll(methodColumn, timeColumn, comparisonsColumn, movementsColumn);
        tableView.getItems().addAll(results);

        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox);
        resultsStage.setScene(scene);
        resultsStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static class SortingResult {
        String method;
        long time;
        int comparisons;
        int movements;

        public SortingResult(String method, long time, int comparisons, int movements) {
            this.method = method;
            this.time = time;
            this.comparisons = comparisons;
            this.movements = movements;
        }
    }
}
