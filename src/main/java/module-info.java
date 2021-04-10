module org.stancuMihai {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.stancuMihai to javafx.fxml;
    exports org.stancuMihai;
    exports org.stancuMihai.controller;
    exports org.stancuMihai.service;
    exports org.stancuMihai.dao.clientDao;
    exports org.stancuMihai.dao;
    exports org.stancuMihai.model;
    opens org.stancuMihai.controller to javafx.fxml;
}