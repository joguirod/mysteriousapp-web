package br.com.mysterious.mysteriousapi.presentation.dtos.response;

public record OrderItemResponseDTO(
        Long itemId,
        Long productId,
        int quantity,
        Double price
){
}
