package org.stancuMihai.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.stancuMihai.model.ProductOrder;
import org.stancuMihai.service.OrderService;
import org.stancuMihai.util.TextGenerator;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {


    @FXML
    public ScrollPane scrollPane;
    @FXML
    public GridPane gridPane;
    @FXML
    public Button addButton;
    @FXML
    public Button editButton;
    @FXML
    public Button viewAllButton;
    @FXML
    public Button deleteButton;
    @FXML
    public Spinner<Integer> idSpinner;
    @FXML
    public Spinner<Integer> productIdSpinner;
    @FXML
    public Spinner<Integer> clientIdSpinner;
    @FXML
    public TextArea messagesArea;
    private OrderService orderService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setOrderService(OrderService.getInstance());
        initSpinners();
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void addOrder() {
        ProductOrder order = new ProductOrder();
        order.setClientId(clientIdSpinner.getValue());
        order.setProductId(productIdSpinner.getValue());
    }

    public void editOrder() throws SQLException {
        Integer id = idSpinner.getValue();
        ProductOrder productOrder = orderService.findById(id);
        if (productOrder.getId() == null) {
            messagesArea.appendText("Could not find order with id " + id);
        } else {
            messagesArea.setText("Found order : " + " Id: " + productOrder.getId() + ", clientId: " +
                    productOrder.getClientId() +
                    ", productId: " + productOrder.getProductId());

            productOrder.setClientId(clientIdSpinner.getValue());
            productOrder.setProductId(productIdSpinner.getValue());
            TextGenerator.textOrderGenerator(messagesArea, "Update", productOrder);
        }
    }

    public void deleteOrder(ActionEvent actionEvent) throws SQLException {
        Integer id = idSpinner.getValue();
        ProductOrder productOrder = orderService.findById(id);
        ProductOrder order = orderService.delete(id);
        if (order.getId() == null) {
            messagesArea.appendText("Could not find order with id " + id);
        } else {
            TextGenerator.textOrderGenerator(messagesArea, "Deleted", productOrder);
        }
    }

    public void selectAll(ActionEvent actionEvent) throws SQLException {
        List<ProductOrder> orders = orderService.selectAll();
        gridPane.getChildren().clear();
        for (int i = 0; i < orders.size(); i++) {
            Button button = new Button();
            button.setPrefSize(120, 30);
            gridPane.add(new Button(orders.get(i).getId() + "|" + orders.get(i).getClientId() + "|" +
                    orders.get(i).getProductId()), 0, i);
        }
    }

    public void initSpinners() {
        productIdSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
        idSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000));
        clientIdSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000));
    }

}
