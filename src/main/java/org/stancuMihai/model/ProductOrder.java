package org.stancuMihai.model;

/***
 * Order pojo database class
 */
public class ProductOrder {

    private Integer id;
    private Integer clientId;
    private Integer productId;
    private Integer quantity;

    public ProductOrder() {
    }

    /***
     * It constructs ProductOrder object
     * @param clientId client foreign key
     * @param productId product foreign key
     * @param quantity quantity of the order
     */
    public ProductOrder(Integer clientId, Integer productId, Integer quantity) {
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public ProductOrder(Integer id, Integer clientId, Integer productId, Integer quantity) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
