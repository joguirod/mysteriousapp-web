package br.com.mysterious.mysteriousapi.presentation.controllers;

import br.com.mysterious.mysteriousapi.application.usecases.product.ListAllProductsUseCase;
import br.com.mysterious.mysteriousapi.presentation.dtos.mappers.ProductDTOMapper;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.ProductResponseDTO;
import br.com.mysterious.mysteriousapi.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    ProductRepository productRepository;
    ListAllProductsUseCase listAllProductsUseCase;
    ProductDTOMapper productDTOMapper;

    public ProductController(ProductRepository productRepository, ListAllProductsUseCase listAllProductsUseCase) {
        this.productRepository = productRepository;
        this.listAllProductsUseCase = listAllProductsUseCase;
        this.productDTOMapper = new ProductDTOMapper();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> productResponseDTOList = listAllProductsUseCase.execute()
                .stream()
                .map(product -> productDTOMapper.toProductResponseDTO(product))
                .toList();

        return new ResponseEntity<>(productResponseDTOList, HttpStatus.OK);
    }
}
