package org.stancuMihai.service;

import org.stancuMihai.dao.AbstractDao;
import org.stancuMihai.model.ProductOrder;

import java.sql.SQLException;
import java.util.List;

public class OrderService {

    public static AbstractDao<ProductOrder> orderDataAccessService;
    public static OrderService orderService = null;

    private OrderService() {
        OrderService.orderDataAccessService = new AbstractDao<>(ProductOrder.class);
    }

    public static OrderService getInstance() {
        if (orderService == null) {
            orderService = new OrderService();
        }
        return orderService;
    }

    public ProductOrder findById(Integer id) throws SQLException {
        return orderDataAccessService.findById(id);
    }

    public ProductOrder update(Integer id, ProductOrder model) throws SQLException {
        return orderDataAccessService.update(id, model);
    }

    public ProductOrder create(ProductOrder model) throws SQLException {
        return orderDataAccessService.create(model);
    }

    public ProductOrder delete(Integer id) throws SQLException {
        return orderDataAccessService.delete(id);
    }

    public List<ProductOrder> selectAll() throws SQLException {
        return orderDataAccessService.selectAll();
    }
}


