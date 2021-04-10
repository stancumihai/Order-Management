package org.stancuMihai.connector;

import javafx.scene.Scene;

import java.sql.*;

public class ConnectionFactory {

    private static Scene scene;
    private static final String userName = "root";
    private static final String password = "hateschool12345";
    private static final String url = "jdbc:mysql://localhost:3306/aplicatie";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, userName, password);
            if (connection != null) {
                System.out.println("Connection established");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
