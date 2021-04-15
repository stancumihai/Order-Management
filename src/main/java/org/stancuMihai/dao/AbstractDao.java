package org.stancuMihai.dao;

import org.stancuMihai.connector.ConnectionFactory;
import org.stancuMihai.util.Construction;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AbstractDao<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDao.class.getName());

    protected Class<T> type;
    public Construction<T> constructor = new Construction<>();

    public AbstractDao(Class<T> type) {
        this.type = type;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    if (field.getName().contains("id") || field.getName().endsWith("Id")) {
                        Integer id = Integer.parseInt(value.toString());
                        method.invoke(instance, id);
                    } else {
                        method.invoke(instance, value);
                    }
                }
                System.out.println(instance);
                list.add(instance);
            }
        } catch (IllegalAccessException | SecurityException | IllegalArgumentException | InvocationTargetException | SQLException | IntrospectionException | InstantiationException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<T> selectAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "Select * from " + type.getSimpleName();
        System.out.println(query);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(Objects.requireNonNull(resultSet));
            ConnectionFactory.close(Objects.requireNonNull(connection));
            ConnectionFactory.close(statement);
        }
        return null;
    }

    public T findById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "Select * from " + type.getSimpleName() + " where id=?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(Objects.requireNonNull(resultSet));
            ConnectionFactory.close(Objects.requireNonNull(connection));
            ConnectionFactory.close(statement);
        }
        return null;
    }


    public T delete(Integer id) throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;
        String query = "Delete from " + type.getSimpleName() + " where id=?";
        T instance = findById(id);
        if (instance != null) {
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
            } finally {
                ConnectionFactory.close(Objects.requireNonNull(connection));
                ConnectionFactory.close(Objects.requireNonNull(statement));
            }
            return instance;
        } else return null;
    }


    public String insertQuery(List<String> attributes) {
        StringBuilder attributesQueryPart = new StringBuilder();
        StringBuilder signQueryPart = new StringBuilder();

        for (int i = 1; i < attributes.size(); i++) {
            attributesQueryPart.append(attributes.get(i));
            signQueryPart.append("?");
            if (i != attributes.size() - 1) {
                attributesQueryPart.append(",");
                signQueryPart.append(",");
            }
        }

        return "insert into " + type.getSimpleName().toLowerCase(Locale.ROOT) +
                "(" + attributesQueryPart + ")" + " values" +
                "(" + signQueryPart + ")";

    }

    public T create(T model) throws SQLException {

        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;
        List<String> attributes = constructor.constructFields(type);
        String query = insertQuery(attributes);
        try {

            List<String> fields = constructor.constructFields(type);
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            for (int i = 1; i < fields.size(); i++) {
                statement.setString(i, fields.get(i));
                System.out.println(i + " " + fields.get(i));
            }
            resultSet = statement.executeQuery(query);

            for (Field field : type.getDeclaredFields()) {
                Object value = resultSet.getObject(field.getName());
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                Method method = propertyDescriptor.getWriteMethod();
                if (field.getName().equals("id")) {
                    Integer id = Integer.parseInt(value.toString());
                    method.invoke(model, id);
                } else {
                    method.invoke(model, value);
                }
            }

            return model;
        } catch (SQLException | IntrospectionException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:create " + e.getMessage());
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }

    public T update(Integer id, T model) throws SQLException {
        Connection connection;
        PreparedStatement statement;
        String query = "UPDATE client set name=?,email=?,address=?,age=? where id=?";
        T instance = findById(id);
        try {

            List<String> fields = constructor.constructFields(type);
            List<String> types = constructor.constructTypes(type);
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, fields.get(1));
            statement.setString(3, fields.get(2));
            statement.setString(4, fields.get(3));
            statement.setString(5, fields.get(4));

            for (int i = 2; i < fields.size(); i++) {
                if (types.get(i).startsWith("Str")) {
                    statement.setString(i, fields.get(i - 1));
                }
            }
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        }
        return instance;
    }
}
