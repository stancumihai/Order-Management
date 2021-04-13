package org.stancuMihai.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import org.stancuMihai.model.Client;
import org.stancuMihai.service.ClientService;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    public ClientService clientService;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setClientService(ClientService.getInstance());
        ageSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public void addClient() {
        Client client = new Client();
        client.setName(nameTextField.getText());
        client.setEmail(emailTextField.getText());
        client.setAddress(addressTextField.getText());
        client.setAge(ageSpinner.getValue());
        clientService.create(client);
    }

    public void editClient() {

    }

    public void viewAllClients(ActionEvent actionEvent) {
    }

    public void deleteClient(ActionEvent actionEvent) {
    }
}
