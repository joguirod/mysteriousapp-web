package br.com.mysterious.mysteriousapi.domain.entities.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produto")
@EqualsAndHashCode
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_produto")
    private Long productId;
    @Column(name = "nome_produto")
    private String productName;
    @Column(name = "preco")
    private Double price;
    @Column(name = "quantidade_estoque")
    private int quantity;
    @Column(name = "descricao")
    private String description;
    @Column(name = "url_foto_produto")
    private String productPictureUrl;

    public Product() {
    }

    public Product(Long productId) {
        this.productId = productId;
    }

    public Product(Long productId, String productName, Double price, int quantity, String description) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public Product(String productName, Double price, int quantity, String description) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductPictureUrl() {
        return productPictureUrl;
    }

    public void setProductPictureUrl(String productPictureUrl) {
        this.productPictureUrl = productPictureUrl;
    }
}
