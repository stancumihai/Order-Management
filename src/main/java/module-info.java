module org.stancuMihai {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.stancuMihai to javafx.fxml;
    exports org.stancuMihai;
}