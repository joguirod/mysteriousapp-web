package br.com.mysterious.mysteriousapi.presentation.dtos.mappers;

import br.com.mysterious.mysteriousapi.domain.entities.product.Product;
import br.com.mysterious.mysteriousapi.presentation.dtos.request.ProductRequestDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.ProductResponseDTO;

public class ProductDTOMapper {
    public ProductResponseDTO toProductResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getProductId(),
                product.getProductName(),
                product.getQuantity(),
                product.getPrice(),
                product.getDescription()
        );
    }

    public Product toProduct(ProductResponseDTO productResponseDTO) {
        return new Product(
                productResponseDTO.getId(),
                productResponseDTO.getName(),
                productResponseDTO.getPrice(),
                productResponseDTO.getQuantity(),
                productResponseDTO.getDescription()
        );
    }

    public Product toProduct(ProductRequestDTO productRequestDTO) {
        return new Product(productRequestDTO.getName(),
                productRequestDTO.getPrice(),
                productRequestDTO.getQuantity(),
                productRequestDTO.getDescription()
        );
    }
}
