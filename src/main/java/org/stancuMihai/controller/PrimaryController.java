package org.stancuMihai.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import org.stancuMihai.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
