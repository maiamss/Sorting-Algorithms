module sorting_algorithm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.swing;
    requires javafx.media;
    requires javafx.web;

    opens sorting_algorithm to javafx.fxml;
    exports sorting_algorithm;
}
