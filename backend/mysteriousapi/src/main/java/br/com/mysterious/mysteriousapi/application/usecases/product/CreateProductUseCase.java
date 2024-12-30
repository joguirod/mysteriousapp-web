package br.com.mysterious.mysteriousapi.application.usecases.product;

import br.com.mysterious.mysteriousapi.application.mappers.ProductMapper;
import br.com.mysterious.mysteriousapi.domain.entities.product.Product;
import br.com.mysterious.mysteriousapi.persistence.entities.ProductEntity;
import br.com.mysterious.mysteriousapi.repositories.ProductRepository;

public class CreateProductUseCase {
    ProductRepository productRepository;
    ProductMapper productMapper;

    public CreateProductUseCase(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Product execute(Product product) {
        validateEmptyValues(product);
        ProductEntity productEntity = productMapper.toEntity(product);
        productEntity = productRepository.save(productEntity);

        return productMapper.toDomainObject(productEntity);
    }

    private void validateEmptyValues(Product product) {
        if (product.getProductName() == null) throw new IllegalArgumentException("Product name is required");
        if (product.getPrice() == null) throw new IllegalArgumentException("Price is required");
        if (product.getQuantity() < 0) throw new IllegalArgumentException("Quantity cannot be less than 0");
    }
}
