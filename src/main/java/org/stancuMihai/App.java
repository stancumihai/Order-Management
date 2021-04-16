package org.stancuMihai;

import com.itextpdf.text.DocumentException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

/***
 * The main class of the app that extends Application
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(loadFXML(), 650, 500);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("tabPaneController" + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws IOException, DocumentException, URISyntaxException {
        launch();

    }
}