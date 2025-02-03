package br.com.mysterious.mysteriousapi.domain.entities.order;

import br.com.mysterious.mysteriousapi.domain.entities.product.Genre;
import br.com.mysterious.mysteriousapi.domain.entities.product.Product;
import br.com.mysterious.mysteriousapi.domain.entities.product.ProductCategory;
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
    @JoinColumn(name = "id_categoria")
    private ProductCategory category;
    @ManyToOne
    @JoinColumn(name = "id_genero")
    private Genre genre;
    @Column(name = "quantidade")
    private int quantity;
    @Column(name = "preco")
    private Double price;

    public OrderItem() {
    }

    public OrderItem(Long orderItemId, MysteriousOrder mysteriousOrder, ProductCategory category, Genre genre, int quantity, Double price) {
        this.orderItemId = orderItemId;
        this.mysteriousOrder = mysteriousOrder;
        this.category = category;
        this.genre = genre;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public MysteriousOrder getMysteriousOrder() {
        return mysteriousOrder;
    }

    public void setMysteriousOrder(MysteriousOrder mysteriousOrder) {
        this.mysteriousOrder = mysteriousOrder;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
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
