module org.stancuMihai {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.stancuMihai to javafx.fxml;
    exports org.stancuMihai;
    exports org.stancuMihai.controller;
    opens org.stancuMihai.controller to javafx.fxml;
}