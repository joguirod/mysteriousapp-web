package br.com.mysterious.mysteriousapi.presentation.dtos.mappers;

import br.com.mysterious.mysteriousapi.domain.entities.product.Product;
import br.com.mysterious.mysteriousapi.presentation.dtos.request.ProductRequestDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.CategoryIncomeResponseDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.GenreIncomeResponseDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.ProductResponseDTO;

import java.util.ArrayList;
import java.util.List;

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

    public List<CategoryIncomeResponseDTO> toResponseCategoryIncomeDTOList(List<Object[]> categories) {
        List<CategoryIncomeResponseDTO> dtoList = new ArrayList<>();

        for (Object[] category : categories) {
            dtoList.add(this.toCategoryIncomeResponseDTO(category));
        }

        return dtoList;
    }

    public CategoryIncomeResponseDTO toCategoryIncomeResponseDTO(Object[] object) {
        return new CategoryIncomeResponseDTO(
                (String) object[0],
                (Long) object[1]
        );
    }

    public List<GenreIncomeResponseDTO> toResponseGenreIncomeDTOList(List<Object[]> genres) {
        List<GenreIncomeResponseDTO> dtoList = new ArrayList<>();

        for (Object[] genre : genres) {
            dtoList.add(this.toGenreIncomeResponseDTO(genre));
        }

        return dtoList;
    }

    public GenreIncomeResponseDTO toGenreIncomeResponseDTO(Object[] object) {
        return new GenreIncomeResponseDTO(
                (String) object[0],
                (Long) object[1]
        );
    }
}
