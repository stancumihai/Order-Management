package org.stancuMihai.dao;

public interface ApplicationDao<T> {

    T findById(Integer id);

    T update(Integer id,T model);

    T create(T model);

    T delete(Integer id);
}
