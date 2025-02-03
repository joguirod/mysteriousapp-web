package br.com.mysterious.mysteriousapi.domain.entities.order;

import br.com.mysterious.mysteriousapi.domain.entities.product.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "itempedido")
@SecondaryTable(name = "pedido", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id_pedido"))
@SecondaryTable(name = "produto", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id_produto"))

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_item_pedido")
    private Long orderItemId;
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private MysteriousOrder mysteriousOrder;
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Product product;
    @Column(name = "quantidade")
    private int quantity;
    @Column(name = "preco")
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
