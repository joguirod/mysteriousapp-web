package br.com.mysterious.mysteriousapi.common.config;

import br.com.mysterious.mysteriousapi.application.mappers.ProductMapper;
import br.com.mysterious.mysteriousapi.application.usecases.product.CreateProductUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.product.ListAllProductsUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.product.UpdateProductUseCase;
import br.com.mysterious.mysteriousapi.repositories.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {
    @Bean
    ProductMapper productMapper() {
        return new ProductMapper();
    }

    @Bean
    ListAllProductsUseCase listAllProductsUseCase(ProductRepository productRepository, ProductMapper productMapper) {
        return new ListAllProductsUseCase(productRepository, productMapper);
    }

    @Bean
    CreateProductUseCase createProductUseCase(ProductRepository productRepository, ProductMapper productMapper) {
        return new CreateProductUseCase(productRepository, productMapper);
    }

    @Bean
    UpdateProductUseCase updateProductUseCase(ProductRepository productRepository, ProductMapper productMapper) {
        return new UpdateProductUseCase(productRepository, productMapper);
    }
}
