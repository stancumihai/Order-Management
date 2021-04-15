package org.stancuMihai.service;

import org.stancuMihai.dao.AbstractDao;
import org.stancuMihai.model.Product;
import org.stancuMihai.model.ProductOrder;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {

    public static AbstractDao<ProductOrder> orderDataAccessService;
    public static OrderService orderService = null;
    public static ProductService productService = ProductService.getInstance();

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

    public Double getTotalSumId(Integer id) throws SQLException {
        List<ProductOrder> orders = selectAll();
        List<ProductOrder> clientRelated = orders.stream().filter(s -> s.getClientId().equals(id)).collect(Collectors.toList());
        double orderPrice = 0;
        int i = 0;
        for (ProductOrder productOrder : clientRelated) {
            orderPrice = orderPrice + productService.findById(productOrder.getProductId()).getPrice() * clientRelated.get(i).getQuantity();
            i++;
        }
        return orderPrice;
    }

    public ProductOrder create(ProductOrder model) throws SQLException {
        Product product = productService.findById(model.getProductId());
        int remainder = product.getQuantity() - model.getQuantity();
        product.setQuantity(remainder);
        if (remainder > 0) {
            productService.update(product.getId(), product);
            return orderDataAccessService.create(model);
        } else return null;
    }

    public ProductOrder delete(Integer id) throws SQLException {
        return orderDataAccessService.delete(id);
    }

    public List<ProductOrder> selectAll() throws SQLException {
        return orderDataAccessService.selectAll();
    }
}


