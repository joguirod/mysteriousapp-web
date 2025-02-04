package br.com.mysterious.mysteriousapi.domain.entities.product;

import jakarta.persistence.*;

@Entity
@Table(name = "categoriaproduto")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long id;
    @Column(name = "descricao")
    private String description;
    @Column(name = "preco")
    private Double value;
    @Column(name = "quantidade_disponivel")
    private int quantityAvailable;

    public ProductCategory(Long id, String description, Double value, int quantityAvailable) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.quantityAvailable = quantityAvailable;
    }

    public ProductCategory(Long id, String description, Double value) {
        this.id = id;
        this.description = description;
        this.value = value;
    }

    public ProductCategory() {
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
}
