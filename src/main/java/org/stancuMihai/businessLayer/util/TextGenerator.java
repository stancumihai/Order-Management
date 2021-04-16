package org.stancuMihai.businessLayer.util;

import javafx.scene.control.TextArea;
import org.stancuMihai.model.Client;
import org.stancuMihai.model.Product;
import org.stancuMihai.model.ProductOrder;

/***
 *Generates text for the message are of the GUI
 */
public class TextGenerator {

    public static void textClientGenerator(TextArea messagesArea, String method, Client client) {
        messagesArea.setText(method + " client : " + " Name: " + client.getName() + ", Email: " + client.getEmail() +
                ", Address: " + client.getAddress() + ", Age: " + client.getAge());
    }

    public static void textOrderGenerator(TextArea messagesArea, String method, ProductOrder product) {
        messagesArea.setText(method + " Order : " + " Id: " + product.getId() + "ClientId: " + product.getClientId()
                + "  ProductId: " + product.getProductId() + " Quantity: " + product.getQuantity());
    }

    public static void textProductGenerator(TextArea messagesArea, String method, Product product) {
        messagesArea.setText(method + " Product : " + "Name" + product.getName() + "price: " + product.getPrice() + "quantity: "
                + product.getQuantity());
    }
}
