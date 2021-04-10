package org.stancuMihai.dao.clientDao;

import org.stancuMihai.connector.ConnectionFactory;
import org.stancuMihai.dao.ApplicationDao;
import org.stancuMihai.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDataAccessService implements ApplicationDao<Client> {


    @Override
    public Client findById(Integer id) {
        Client client = new Client();

        try {
            Connection connection = ConnectionFactory.getConnection();
            String sql = "Select * from client where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public Client update(Integer id, Client model) throws SQLException {

        Connection connection = ConnectionFactory.getConnection();
        String sql = "UPDATE client set name=? where id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, model.getName());
        statement.setInt(2, id);
        statement.executeUpdate();
        System.out.println(model);
        return model;
    }

    @Override
    public Client create(Client model) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "insert into client(name) values(?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return model;
    }

    @Override
    public Client delete(Integer id) throws SQLException {

        Client client = findById(id);
        Connection connection = ConnectionFactory.getConnection();
        String sql = "Delete from client where id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        System.out.println(client);
        return client;
    }

    @Override
    public List<Client> selectAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        String sql = "Select * from client";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            clients.add(new Client(resultSet.getInt("id"),
                    resultSet.getString("name")));
            System.out.println(clients.get(clients.size() - 1));
        }
        return clients;
    }
}
