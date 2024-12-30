package br.com.mysterious.mysteriousapi.application.usecases.product;

import br.com.mysterious.mysteriousapi.application.exceptions.ProductNotFoundException;
import br.com.mysterious.mysteriousapi.application.mappers.ProductMapper;
import br.com.mysterious.mysteriousapi.domain.entities.product.Product;
import br.com.mysterious.mysteriousapi.persistence.entities.ProductEntity;
import br.com.mysterious.mysteriousapi.repositories.ProductRepository;

import java.util.Optional;

public class UpdateProductUseCase {
    ProductRepository productRepository;
    ProductMapper productMapper;

    public UpdateProductUseCase(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Product execute(Product product) throws ProductNotFoundException {
        ProductEntity productEntity = productRepository.findById(product.getId())
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + product.getId() + " not found")
        );
        validate(product);

        productEntity.setProductName(product.getProductName());
        productEntity.setPrice(product.getPrice());
        productEntity.setQuantity(product.getQuantity());
        productEntity.setDescription(product.getDescription());
        productEntity = productRepository.save(productEntity);

        return productMapper.toDomainObject(productEntity);
    }

    private void validate(Product product) throws ProductNotFoundException {
        if (product.getPrice() == null) {
            throw new IllegalArgumentException("Price is required");
        } else if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Price must be a positive number");
        }
        if (product.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity must be a positive number");
        }
    }
}
