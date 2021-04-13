package org.stancuMihai.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.stancuMihai.model.Client;
import org.stancuMihai.service.ClientService;
import org.stancuMihai.util.TextGenerator;
import org.stancuMihai.validator.AppValidation;
import org.stancuMihai.validator.UserValidation;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

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

    public void addClient() {
        Client client = new Client();
        client.setName(nameTextField.getText());
        client.setEmail(emailTextField.getText());
        client.setAddress(addressTextField.getText());
        client.setAge(ageSpinner.getValue());
        clientService.create(client);
        TextGenerator.textGenerator(messagesArea, "Added", client);
    }

    public void editClient() throws SQLException {
        Integer id = idSpinner.getValue();
        Client client = clientService.findById(id);
        if (client.getId() == null) {
            messagesArea.appendText("Could not find client with id " + id);
        } else {
            messagesArea.setText("Found client : " + " Name: " + client.getName() + ", Email: " + client.getEmail() +
                    ", Address: " + client.getAddress() + ", Age: " + client.getAge());
            client.setName(nameTextField.getText());
            client.setEmail(emailTextField.getText());
            client.setAddress(addressTextField.getText());
            client.setAge(ageSpinner.getValue());
            clientService.update(id, client);
            TextGenerator.textGenerator(messagesArea, "Update", client);
        }
    }

    public void viewAllClients() throws SQLException {
        List<Client> clients = clientService.selectAll();
        gridPane.getChildren().clear();
        for (int i = 0; i < clients.size(); i++) {
            Button button = new Button();
            button.setPrefSize(120, 30);
            gridPane.add(new Button(clients.get(i).getId() + "|" + clients.get(i).getName() + "|" + clients.get(i).getEmail() + "|" +
                    clients.get(i).getAddress() + "|" + clients.get(i).getAge()), 0, i);
        }
    }

    public void deleteClient() throws SQLException {
        Integer id = idSpinner.getValue();
        Client client = clientService.delete(id);
        if (client.getId() == null) {
            messagesArea.appendText("Could not find client with id " + id);
        } else {
            TextGenerator.textGenerator(messagesArea, "Deleted", client);
        }
    }

    void initSpinners() {
        ageSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
        idSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000));
    }

    void initGridPane() {
        for (int i = 0; i < 100; i++) {

        }
    }
}
