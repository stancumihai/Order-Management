package org.stancuMihai.connector;

import java.sql.*;

public class ConnectionFactory {

    private static final String userName = "root";
    private static final String password = "hateschool12345";
    private static final String url = "jdbc:mysql://localhost:3306/aplicatie";

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

    public static void close(ResultSet resultSet) throws SQLException {
        resultSet.close();
    }

    public static void close(Statement statement) throws SQLException {
        statement.close();
    }

    public static void close(Connection connection) throws SQLException {
        connection.close();
    }
}
