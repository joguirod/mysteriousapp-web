package br.com.mysterious.mysteriousapi.presentation.dtos.response;

public record OrderItemResponseDTO(
        Long itemId,
        CategoryResponseDTO category,
        GenreResponseDTO genre,
        int quantity,
        Double price
){
}
