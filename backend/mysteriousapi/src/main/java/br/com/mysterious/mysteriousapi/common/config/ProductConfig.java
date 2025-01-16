package br.com.mysterious.mysteriousapi.common.config;

import br.com.mysterious.mysteriousapi.application.usecases.product.CreateProductUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.product.DeleteProductUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.product.ListAllProductsUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.product.UpdateProductUseCase;
import br.com.mysterious.mysteriousapi.persistence.repositories.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    ListAllProductsUseCase listAllProductsUseCase(ProductRepository productRepository) {
        return new ListAllProductsUseCase(productRepository);
    }

    @Bean
    CreateProductUseCase createProductUseCase(ProductRepository productRepository) {
        return new CreateProductUseCase(productRepository);
    }

    @Bean
    UpdateProductUseCase updateProductUseCase(ProductRepository productRepository) {
        return new UpdateProductUseCase(productRepository);
    }

    @Bean
    DeleteProductUseCase deleteProductUseCase(ProductRepository productRepository) {
        return new DeleteProductUseCase(productRepository);
    }
}
