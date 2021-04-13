package org.stancuMihai.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OrderController
{

    @FXML
    public Button addButton;
    @FXML
    public Button editButton;
    @FXML
    public Button viewAllButton;
    @FXML
    public Button deleteButton;
    public Spinner<Integer> idSpinner;
    public Spinner<Integer> productIdSpinner;
    @FXML
    public TextArea messagesArea;
    @FXML
    public Spinner<Integer> clientIdSpinner;


    public void addOrder(ActionEvent actionEvent) {
    }

    public void editOrder(ActionEvent actionEvent) {
    }

    public void deleteOrder(ActionEvent actionEvent) {
    }

    public void viewAllOrders(ActionEvent actionEvent) {
    }
}
