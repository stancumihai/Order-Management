module org.stancuMihai {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.jetbrains.annotations;
    requires itextpdf;
    requires org.junit.jupiter.api;

    opens org.stancuMihai to javafx.fxml;
    exports org.stancuMihai;
    exports org.stancuMihai.businessLayer.service;
    exports org.stancuMihai.dataAccessLayer;
    exports org.stancuMihai.model;
    exports org.stancuMihai.businessLayer.validator;
    exports org.stancuMihai.businessLayer.util;
    exports org.stancuMihai.presentation.controller;
    opens org.stancuMihai.presentation.controller to javafx.fxml;
}