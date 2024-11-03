

## Pré-requisitos

- Java Development Kit (JDK) instalado
- JavaFX SDK configurado no seu sistema


## Compilação e execução

1. Navegue até a pasta `/src` do projeto.

2. Execute o seguinte comando para compilar o código:

```
    javac -Xlint:unchecked --module-path "<CAMINHO_DO_JAVAFX>\lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.base,javafx.media,javafx.web sorting_algorithm/*.java
```

3.execute com o comando 

```
    java --module-path "<CAMINHO_DO_JAVAFX>\lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.base,javafx.media,javafx.web sorting_algorithm.SortingApp
```