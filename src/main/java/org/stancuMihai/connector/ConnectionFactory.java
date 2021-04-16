package org.stancuMihai.connector;

import java.sql.*;

/***
 * Class that is used in the dao for connection to the database
 */
public class ConnectionFactory {

    private static final String userName = "root";
    private static final String password = "hateschool12345";
    private static final String url = "jdbc:mysql://localhost:3306/aplicatie";

    /***
     *
     * @return it returns the connection to the database
     * @throws SQLException  DriverManager.getConnection generates it
     */
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

    /***
     *
     * @param resultSet the ResultSet that is passed
     * @throws SQLException close method generates it
     */
    public static void close(ResultSet resultSet) throws SQLException {
        resultSet.close();
    }

    /***
     *
     * @param statement the Statement set that is passed
     * @throws SQLException close method generates it
     */
    public static void close(Statement statement) throws SQLException {
        statement.close();
    }

    /***
     *
     * @param connection the Connection set that is passed
     * @throws SQLException close method generates it
     */
    public static void close(Connection connection) throws SQLException {
        connection.close();
    }
}
