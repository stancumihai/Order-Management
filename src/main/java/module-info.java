module org.stancuMihai {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens org.stancuMihai to javafx.fxml;
    exports org.stancuMihai;
    exports org.stancuMihai.controller;
    exports org.stancuMihai.service;
    exports org.stancuMihai.dao;
    exports org.stancuMihai.model;
    exports org.stancuMihai.validator;
    exports org.stancuMihai.util;
    opens org.stancuMihai.controller to javafx.fxml;
}