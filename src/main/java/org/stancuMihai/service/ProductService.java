package org.stancuMihai.service;

import org.stancuMihai.dao.AbstractDao;
import org.stancuMihai.model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {


    public static AbstractDao<Product> productDataAccessService;
    public static ProductService productService = null;

    private ProductService() {
        productDataAccessService = new AbstractDao<>(Product.class);
    }

    public static ProductService getInstance() {
        if (productService == null) {
            productService = new ProductService();
        }
        return productService;
    }

    public Product findById(Integer id) throws SQLException {
        return productDataAccessService.findById(id);
    }

    public Product update(Integer id, Product model) throws SQLException {
        return productDataAccessService.update(id, model);
    }

    public Product create(Product model) throws SQLException {
        return productDataAccessService.create(model);
    }

    public Product delete(Integer id) throws SQLException {
        return productDataAccessService.delete(id);
    }

    public List<Product> selectAll() throws SQLException {
        return productDataAccessService.selectAll();
    }
}

