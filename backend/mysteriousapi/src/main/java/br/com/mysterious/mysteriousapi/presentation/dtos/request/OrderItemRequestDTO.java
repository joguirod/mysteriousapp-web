package br.com.mysterious.mysteriousapi.presentation.dtos.request;

public record OrderItemRequestDTO (
        Long productId,
        int quanity,
        Double price
){
}
