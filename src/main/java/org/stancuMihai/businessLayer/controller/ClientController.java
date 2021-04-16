package org.stancuMihai.businessLayer.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.stancuMihai.businessLayer.exception.NotValidDataEntered;
import org.stancuMihai.businessLayer.service.ClientService;
import org.stancuMihai.businessLayer.util.TextGenerator;
import org.stancuMihai.businessLayer.validator.AppValidation;
import org.stancuMihai.businessLayer.validator.UserValidation;
import org.stancuMihai.model.Client;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/***
 * It makes the chain between GUI and logic for the client window
 */
public class ClientController implements Initializable {


    private ClientService clientService;
    private AppValidation appValidation;

    @FXML
    public GridPane gridPane;
    @FXML
    public ScrollPane scrollPane;
    @FXML
    public Button addButton;
    @FXML
    public Button editButton;
    @FXML
    public Button viewAllButton;
    @FXML
    public Button deleteButton;
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField emailTextField;
    @FXML
    public TextField addressTextField;
    @FXML
    public Spinner<Integer> ageSpinner;
    @FXML
    public Spinner<Integer> idSpinner;
    @FXML
    public TextArea messagesArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setClientService(ClientService.getInstance());
        setAppValidation(new UserValidation());
        initSpinners();
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public void setAppValidation(AppValidation appValidation) {
        this.appValidation = appValidation;
    }

    public void addClient() throws SQLException {
        Client client = new Client();
        client.setName(nameTextField.getText());
        client.setEmail(emailTextField.getText());
        client.setAddress(addressTextField.getText());
        client.setAge(ageSpinner.getValue());
        try {
            appValidation.validate(client);
            clientService.create(client);
            TextGenerator.textClientGenerator(messagesArea, "Added", client);
        } catch (NotValidDataEntered ne) {
            ne.fillInStackTrace();
            messagesArea.setText("Bad input");
        }

    }

    public void editClient() throws SQLException {
        Integer id = idSpinner.getValue();
        Client client = clientService.findById(id);
        if (client.getId() == null) {
            messagesArea.appendText("Could not find client with id " + id);
        } else {

            TextGenerator.textClientGenerator(messagesArea, "Found", client);
            client.setName(nameTextField.getText());
            client.setEmail(emailTextField.getText());
            client.setAddress(addressTextField.getText());
            client.setAge(ageSpinner.getValue());
            try {
                appValidation.validate(client);
                clientService.update(id, client);
                TextGenerator.textClientGenerator(messagesArea, "Update", client);
            } catch (NotValidDataEntered ne) {
                ne.fillInStackTrace();
                messagesArea.setText("Bad input");
            }
        }
    }

    public void selectAll() throws SQLException {
        List<Client> clients = clientService.selectAll();
        gridPane.getChildren().clear();
        for (int i = 0; i < clients.size(); i++) {
            Button button = new Button();
            button.setPrefSize(120, 30);
            gridPane.add(new Button(clients.get(i).getId() + " | " + clients.get(i).getName() + " | " + clients.get(i).getEmail() + " | " +
                    clients.get(i).getAddress() + " | " + clients.get(i).getAge()), 0, i);
        }
    }

    public void deleteClient() throws SQLException {
        Integer id = idSpinner.getValue();
        Client client = clientService.findById(id);

        if (client.getId() == null) {
            messagesArea.appendText("Could not find client with id " + id);
        } else {
            try {
                clientService.delete(id);
                TextGenerator.textClientGenerator(messagesArea, "Deleted", client);
            } catch (Exception ne) {
                ne.fillInStackTrace();
            }
        }
    }

    void initSpinners() {
        ageSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
        idSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000));
    }
}
