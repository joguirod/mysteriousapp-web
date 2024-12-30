package br.com.mysterious.mysteriousapi.presentation.dtos.mappers;

import br.com.mysterious.mysteriousapi.domain.entities.product.Product;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.ProductResponseDTO;

public class ProductDTOMapper {
    public ProductResponseDTO toProductResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getProductName(),
                product.getQuantity(),
                product.getPrice(),
                product.getDescription()
        );
    }

    public Product toProduct(ProductResponseDTO productResponseDTO) {
        return new Product(
                productResponseDTO.id(),
                productResponseDTO.name(),
                productResponseDTO.quantity(),
                productResponseDTO.price(),
                productResponseDTO.description()
        );
    }
}
