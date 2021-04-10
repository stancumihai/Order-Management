package org.stancuMihai.dao.productDao;

import org.stancuMihai.dao.ApplicationDao;
import org.stancuMihai.model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductDataAccessService implements ApplicationDao<Product> {
    @Override
    public Product findById(Integer id) {
        return null;
    }

    @Override
    public Product update(Integer id, Product model) {
        return null;
    }

    @Override
    public Product create(Product model) {
        return null;
    }

    @Override
    public Product delete(Integer id) {
        return null;
    }

    @Override
    public List<Product> selectAll() throws SQLException {
        return null;
    }
}
