package br.com.mysterious.mysteriousapi.domain.entities.product;

import jakarta.persistence.*;

@Entity
@Table(name = "genero")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero")
    private Long id;
    @Column(name = "descricao")
    private String description;
    @Column(name = "quantidade_disponivel")
    private int quantityAvailable;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_categoria")
    private ProductCategory productCategory;

    public Genre() {
    }

    public Genre(Long id, String description, int quantityAvailable, ProductCategory productCategory) {
        this.id = id;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
        this.productCategory = productCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}
