package br.com.mysterious.mysteriousapi.application.usecases.product;

import br.com.mysterious.mysteriousapi.application.exceptions.NonPositiveNumberException;
import br.com.mysterious.mysteriousapi.application.exceptions.ProductNotFoundException;
import br.com.mysterious.mysteriousapi.application.exceptions.RequiredValueException;
import br.com.mysterious.mysteriousapi.domain.entities.product.Product;
import br.com.mysterious.mysteriousapi.persistence.repositories.ProductRepository;

public class UpdateProductUseCase {
    ProductRepository productRepository;

    public UpdateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(Product product) throws ProductNotFoundException, RequiredValueException, NonPositiveNumberException {
        if (productRepository.findById(product.getProductId()).isEmpty()) {
            throw new ProductNotFoundException("Product with id " + product.getProductId() + " not found");
        }
        validate(product);

        return productRepository.save(product);
    }

    private void validate(Product product) throws ProductNotFoundException, RequiredValueException, NonPositiveNumberException {
        if (product.getPrice() == null) {
            throw new RequiredValueException("Price is required");
        } else if (product.getPrice() < 0) {
            throw new NonPositiveNumberException("Price must be a positive number");
        }
        if (product.getQuantity() < 0) {
            throw new NonPositiveNumberException("Quantity must be a positive number");
        }
    }
}
