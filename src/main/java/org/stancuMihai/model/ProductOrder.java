package org.stancuMihai.model;

public class ProductOrder {

    private Integer id;
    private Integer clientId;
    private Integer productId;


    public ProductOrder() {
    }

    public ProductOrder(Integer id, Integer clientId, Integer productId) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", productId=" + productId +
                '}';
    }
}
