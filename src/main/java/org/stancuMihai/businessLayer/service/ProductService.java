package org.stancuMihai.businessLayer.service;

import org.stancuMihai.dataAccessLayer.AbstractDao;
import org.stancuMihai.model.Product;

import java.sql.SQLException;
import java.util.List;

/***
 * The business logic for the Product Class
 */
public class ProductService {


    public static AbstractDao<Product> productDataAccessService;
    public static ProductService productService = null;

    private ProductService() {
        productDataAccessService = new AbstractDao<>(Product.class);
    }

    /***
     *
     * @return it returns singleton instance of ProductService
     */
    public static ProductService getInstance() {
        if (productService == null) {
            productService = new ProductService();
        }
        return productService;
    }

    public Product findById(Integer id) throws SQLException {
        return productDataAccessService.findById(id);
    }

    public void update(Integer id, Product model) throws SQLException {
        productDataAccessService.update(id, model);
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

