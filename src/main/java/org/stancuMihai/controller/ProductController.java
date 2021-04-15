package org.stancuMihai.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.stancuMihai.model.Product;
import org.stancuMihai.service.ProductService;
import org.stancuMihai.util.TextGenerator;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

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
    public Spinner<Integer> idSpinner;
    @FXML
    public TextField priceTextField;
    @FXML
    public TextArea messagesArea;
    @FXML
    public ScrollPane scrollPane;
    @FXML
    public GridPane gridPane;

    public ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000));
        setProductService(ProductService.getInstance());
    }

    public void addProduct() throws SQLException {
        Product product = new Product();
        product.setName(nameTextField.getText());
        product.setPrice(Double.parseDouble(priceTextField.getText()));
        productService.create(product);
        TextGenerator.textProductGenerator(messagesArea, "Added", product);
    }

    public void editProduct() throws SQLException {
        Integer id = idSpinner.getValue();
        Product product = productService.findById(id);
        if (product.getId() == null) {
            messagesArea.appendText("Could not find product with id " + id);
        } else {
            TextGenerator.textProductGenerator(messagesArea, "Found", product);
            product.setPrice(Double.parseDouble(priceTextField.getText()));
            product.setName(nameTextField.getText());
            productService.update(id, product);
            TextGenerator.textProductGenerator(messagesArea, "Update", product);
        }
    }

    public void deleteProduct() throws SQLException {
        Integer id = idSpinner.getValue();
        Product product = productService.findById(id);
        if (product.getId() == null) {
            messagesArea.appendText("Could not find product with id " + id);
        } else {
            TextGenerator.textProductGenerator(messagesArea, "Found", product);
            product.setPrice(Double.parseDouble(priceTextField.getText()));
            product.setName(nameTextField.getText());
            productService.delete(id);
            TextGenerator.textProductGenerator(messagesArea, "Update", product);
        }
    }

    public void selectAll() throws SQLException {
        List<Product> productList = productService.selectAll();
        gridPane.getChildren().clear();
        for (int i = 0; i < productList.size(); i++) {
            Button button = new Button();
            button.setPrefSize(120, 30);
            gridPane.add(new Button(productList.get(i).getId() + "|" + productList.get(i).getName() + "|" +
                    productList.get(i).getPrice()), 0, i);
        }
    }
}
