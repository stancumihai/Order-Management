package org.stancuMihai.util;

import javafx.scene.control.TextArea;
import org.stancuMihai.model.Client;

public class TextGenerator {

    public static void textGenerator(TextArea messagesArea, String method, Client client) {
        messagesArea.appendText(method + " client : " + " Name: " + client.getName() + ", Email: " + client.getEmail() +
                ", Address: " + client.getAddress() + ", Age: " + client.getAge());
    }
}
