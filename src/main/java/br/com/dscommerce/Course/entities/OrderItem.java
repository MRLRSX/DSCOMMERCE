package br.com.dscommerce.Course.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name="tb_order_item")
@Entity(name="OrderItem")
public class OrderItem {

    @EmbeddedId
    private OrderItemPK orderItemPK = new OrderItemPK();

    private Integer quantity;

    private Double price;

    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        this.quantity = quantity;
        this.price = price;
        this.orderItemPK.setOrder(order);
        this.orderItemPK.setProduct(product);
    }

    public Order getOrder(){
        return this.orderItemPK.getOrder();
    }
    public void setOrder(Order order){
        this.orderItemPK.setOrder(order);
    }
    public Product getProduct(){
        return this.orderItemPK.getProduct();
    }
    public void setProduct(Product product){
        this.orderItemPK.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
