package org.stancuMihai.connector;

import javafx.scene.Scene;

import java.sql.*;

public class ConnectionFactory {

    private static Scene scene;
    private static final String userName = "root";
    private static final String password = "hateschool12345";
    private static final String url = "jdbc:mysql://localhost:3306/aplicatie";


    public static void doSth() throws SQLException {
        String query = "SELECT * FROM location";
        Connection con = DriverManager.getConnection(url, userName, password);
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(query);
        while (result.next()) {
            Long id = result.getLong("id");
            String name = result.getString("name");
            System.out.println(id + "   " + name);
        }
    }
}
