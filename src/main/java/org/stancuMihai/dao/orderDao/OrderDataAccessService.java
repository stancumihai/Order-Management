package org.stancuMihai.dao.orderDao;

import org.stancuMihai.dao.ApplicationDao;
import org.stancuMihai.model.ProductOrder;

import java.sql.SQLException;
import java.util.List;

public class OrderDataAccessService implements ApplicationDao<ProductOrder> {

    @Override
    public ProductOrder findById(Integer id) {
        return null;
    }

    @Override
    public ProductOrder update(Integer id, ProductOrder model) {
        return null;
    }

    @Override
    public ProductOrder create(ProductOrder model) {
        return null;
    }

    @Override
    public ProductOrder delete(Integer id) {
        return null;
    }

    @Override
    public List<ProductOrder> selectAll() throws SQLException {
        return null;
    }
}
