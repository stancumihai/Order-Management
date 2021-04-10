package org.stancuMihai.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.stancuMihai.App;
import org.stancuMihai.model.Client;
import org.stancuMihai.service.ClientService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {


    public ClientService clientService;

    @FXML
    public TextField name;
    @FXML
    public Button dbEnter;
    @FXML
    public TextField id;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }


    public Client findById(Integer id) {
        return clientService.findById(id);
    }

    public Client update(Integer id, Client model) throws SQLException {
        return clientService.update(id, model);
    }

    public void create(Client model) {
        clientService.create(model);
    }

    public Client delete(Integer id) throws SQLException {
        return clientService.delete(id);
    }

    public List<Client> selectAll() throws SQLException {
        return clientService.selectAll();
    }


    public void putInTable() {

        String clientName = name.getText();
        Client client = new Client(clientName);
        create(client);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setClientService(ClientService.getInstance());
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}
