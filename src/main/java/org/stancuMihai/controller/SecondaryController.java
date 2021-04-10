package org.stancuMihai.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import org.stancuMihai.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}