package org.stancuMihai;

import org.stancuMihai.model.Client;

import java.lang.reflect.Field;

public class Reflection {

    public static void main(String[] args) {
        Client client = new Client();
        retrieveProperties(client);

    }

    public static void retrieveProperties(Object object) {

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(field);
                System.out.println(value);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
