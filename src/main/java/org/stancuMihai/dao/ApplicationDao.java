package org.stancuMihai.dao;

import java.sql.SQLException;
import java.util.List;

public interface ApplicationDao<T> {

    T findById(Integer id);

    T update(Integer id, T model) throws SQLException;

    T create(T model);

    T delete(Integer id) throws SQLException;

    List<T> selectAll() throws SQLException;
}
