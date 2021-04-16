package org.stancuMihai.dataAccessLayer;

import org.stancuMihai.connector.ConnectionFactory;
import org.stancuMihai.businessLayer.util.Constructor;

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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/***
 * Class that involves CRUD operations
 * @param <T> generic parameter for easing the the programme
 */
public class AbstractDao<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDao.class.getName());
    protected Class<T> type;
    public Constructor<T> constructor = new Constructor<>();

    /***
     *
     * @param type the Class type
     */
    public AbstractDao(Class<T> type) {
        this.type = type;
    }

    /**
     *
     * @param object it take the object that is going to be examined for values
     * @return it returns the values in the fields for a certain instance of the object
     * @throws IllegalAccessException Generated by the get() method for field
     */
    public List<Object> takeValues(T object) throws IllegalAccessException {
        List<Object> valuesOfClass = new ArrayList<>();
        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            valuesOfClass.add(field.get(object));
        }
        return valuesOfClass;
    }

    /***
     *
     * @param resultSet it takes the result set for looping trough the database
     * @return it returns a list of objects created from the database
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        List<String> types = constructor.constructTypes(type);
        try {
            while (resultSet.next()) {
                int index = 0;
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    if (types.get(index).equals("Int")) {
                        Integer id = Integer.parseInt(value.toString());
                        method.invoke(instance, id);
                    } else {
                        method.invoke(instance, value);
                    }
                    index++;
                }
                list.add(instance);
            }
        } catch (IllegalAccessException | SecurityException | IllegalArgumentException | InvocationTargetException | SQLException | IntrospectionException | InstantiationException e) {
            e.printStackTrace();
        }
        return list;
    }

    /***
     *
     * @return it returns the entire list of objects from the database
     * @throws SQLException it's generated when preparing statements
     */
    public List<T> selectAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "Select * from " + type.getSimpleName();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:SelectAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(Objects.requireNonNull(resultSet));
            ConnectionFactory.close(Objects.requireNonNull(connection));
            ConnectionFactory.close(statement);
        }
        return null;
    }

    /***
     *
     * @param id it represents the id that is going to be searched in the database
     * @return it returns the object with the parameter id if it exists
     * @throws SQLException it's generated when calling ConnectionFactory
     */
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

    /**
     *
     * @param id it represents the id that is going to be searched for deletion in the database
     * @return it returns the object with the parameter id if it exists
     * @throws SQLException it's generated when calling ConnectionFactory
     */
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
                LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
            } finally {
                ConnectionFactory.close(Objects.requireNonNull(connection));
                ConnectionFactory.close(Objects.requireNonNull(statement));
            }
            return instance;
        } else
            return null;
    }

    /**
     *
     * @param model it represents the object that is going to be inserted in the database
     * @return it returns the object with the object that is created for validation
     * @throws SQLException SQLException it's generated when calling ConnectionFactory
     */
    public T create(T model) throws SQLException {

        Connection connection;
        PreparedStatement statement;
        List<String> attributes = constructor.constructFields(type);
        String query = constructor.insertQuery(attributes, type);
        try {
            List<String> types = constructor.constructTypes(type);
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            List<Object> valuesOfClass = takeValues(model);
            for (int i = 1; i < types.size(); i++) {
                if (types.get(i).startsWith("Int")) {
                    statement.setInt(i, (Integer) valuesOfClass.get(i));
                } else if (types.get(i).equals("Float")) {
                    statement.setDouble(i, (Double) valuesOfClass.get(i));
                } else {
                    statement.setString(i, (String) valuesOfClass.get(i));
                }
            }
            statement.executeUpdate();
            return model;
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        }
        return null;
    }

    /***
     *
     * @param id it represents the id that is going to be searched for deletion in the database
     * @param model it represents the object that is going to be updated in the database
     * @return it returns the object taken as parameter if id exists
     * @throws SQLException it's generated when calling ConnectionFactory
     */
    public T update(Integer id, T model) throws SQLException {

        Connection connection;
        PreparedStatement statement;
        List<String> attributes = constructor.constructFields(type);
        String query = constructor.updateQuery(attributes, type);
        try {
            List<String> types = constructor.constructTypes(type);
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            List<Object> valuesOfClass = takeValues(model);
            for (int i = 1; i < types.size(); i++) {
                if (types.get(i).startsWith("Int")) {
                    statement.setInt(i, (Integer) valuesOfClass.get(i));
                } else if (types.get(i).equals("Float")) {
                    statement.setDouble(i, (Double) valuesOfClass.get(i));
                } else {
                    statement.setString(i, (String) valuesOfClass.get(i));
                }
            }
            statement.setInt(attributes.size(), id);
            statement.executeUpdate();
            return model;
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        }
        return null;
    }
}