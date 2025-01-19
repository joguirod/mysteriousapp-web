package br.com.mysterious.mysteriousapi.domain.entities.order;

import br.com.mysterious.mysteriousapi.domain.entities.product.Product;
import jakarta.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderItemId;
    @ManyToOne
    private MysteriousOrder mysteriousOrder;
    @ManyToOne
    private Product product;
    private int quantity;
    private Double price;

    public OrderItem() {
    }

    public OrderItem(Long orderItemId, MysteriousOrder mysteriousOrder, Product product, int quantity, Double price) {
        this.orderItemId = orderItemId;
        this.mysteriousOrder = mysteriousOrder;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public MysteriousOrder getOrder() {
        return mysteriousOrder;
    }

    public void setOrder(MysteriousOrder mysteriousOrder) {
        this.mysteriousOrder = mysteriousOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
