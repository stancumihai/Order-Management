package org.stancuMihai.controller;

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
    public Spinner<Integer> quantitySpinner;
    @FXML
    public TextArea messagesArea;
    @FXML
    public Button purchaseButton;

    private OrderService orderService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setOrderService(OrderService.getInstance());
        initSpinners();
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void addOrder() throws SQLException {
        ProductOrder order = new ProductOrder();
        order.setClientId(clientIdSpinner.getValue());
        order.setProductId(productIdSpinner.getValue());
        order.setQuantity(quantitySpinner.getValue());
        ProductOrder productOrder = orderService.create(order);

        if (productOrder == null) {
            messagesArea.clear();
            messagesArea.setText("Out of stock");
        } else {
            TextGenerator.textOrderGenerator(messagesArea, "Added", order);
        }
    }

    public void editOrder() throws SQLException {
        Integer id = idSpinner.getValue();
        ProductOrder productOrder = orderService.findById(id);
        if (productOrder.getId() == null) {
            messagesArea.setText("Could not find order with id " + id);
        } else {
            TextGenerator.textOrderGenerator(messagesArea, "Found", productOrder);
            productOrder.setClientId(clientIdSpinner.getValue());
            productOrder.setProductId(productIdSpinner.getValue());
            productOrder.setQuantity(quantitySpinner.getValue());
            ProductOrder productOrder1 = orderService.update(id, productOrder);
            if (productOrder1 == null) {
                messagesArea.setText("Could not update order with id " + id);
            }
            TextGenerator.textOrderGenerator(messagesArea, "Update", productOrder);
        }
    }

    public void getTotalSumId() throws SQLException {
        Integer id = idSpinner.getValue();
        Double sum = orderService.getTotalSumId(id);
        messagesArea.clear();
        messagesArea.setText("For client " + id + " total sum is: " + sum);
    }

    public void deleteOrder() throws SQLException {
        Integer id = idSpinner.getValue();
        ProductOrder order = orderService.findById(id);

        if (order.getId() == null) {
            messagesArea.appendText("Could not find order with id " + id);
        } else {
            orderService.delete(id);
            TextGenerator.textOrderGenerator(messagesArea, "Deleted", order);
        }
    }

    public void selectAll() throws SQLException {
        List<ProductOrder> orders = orderService.selectAll();
        gridPane.getChildren().clear();
        for (int i = 0; i < orders.size(); i++) {
            Button button = new Button();
            button.setPrefSize(120, 30);
            gridPane.add(new Button(orders.get(i).getId() + " | " + orders.get(i).getClientId() + " | " +
                    orders.get(i).getProductId() + " | " + orders.get(i).getQuantity()), 0, i);
        }
    }

    public void initSpinners() {
        productIdSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
        idSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000));
        clientIdSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000));
        quantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000));
    }
}
