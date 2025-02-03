package br.com.mysterious.mysteriousapi.common.config;

import br.com.mysterious.mysteriousapi.application.usecases.product.*;
import br.com.mysterious.mysteriousapi.persistence.repositories.GenreRepository;
import br.com.mysterious.mysteriousapi.persistence.repositories.ProductCategoryRepository;
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

    @Bean
    GetCategoriesSoldByMonthUseCase getCategoriesSoldByMonthUseCase(ProductCategoryRepository productCategoryRepository) {
        return new GetCategoriesSoldByMonthUseCase(productCategoryRepository);
    }

    @Bean
    GetCategoriesSoldByYearUseCase getCategoriesSoldByYearUseCase(ProductCategoryRepository productCategoryRepository) {
        return new GetCategoriesSoldByYearUseCase(productCategoryRepository);
    }

    @Bean
    GetCategoriesSoldByEpochUseCase getCategoriesSoldByEpochUseCase(ProductCategoryRepository productCategoryRepository) {
        return new GetCategoriesSoldByEpochUseCase(productCategoryRepository);
    }

    @Bean
    GetGenresSoldByMonthUseCase getGenresSoldByMonthUseCase(GenreRepository genreRepository) {
        return new GetGenresSoldByMonthUseCase(genreRepository);
    }

    @Bean
    GetGenresSoldByYearUseCase getGenresSoldByYearUseCase(GenreRepository genreRepository) {
        return new GetGenresSoldByYearUseCase(genreRepository);
    }

    @Bean
    GetGenresSoldByEpochUseCase getGenresSoldByEpochUseCase(GenreRepository genreRepository) {
        return new GetGenresSoldByEpochUseCase(genreRepository);
    }
}
