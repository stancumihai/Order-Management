package org.stancuMihai.model;

/***
 * Product pojo database class
 */
public class Product {

    private Integer id;
    private String name;
    private Double price;
    private Integer quantity;

    public Product() {

    }

    /***
     * Construct for Product object
     * @param name name of the product
     * @param price price of the product
     * @param quantity quantity of the product
     */
    public Product(String name, Double price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(Integer id, String name, Double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + '\'' +
                ", price=" + price +
                ", quantity" + quantity + "\n";
    }
}
