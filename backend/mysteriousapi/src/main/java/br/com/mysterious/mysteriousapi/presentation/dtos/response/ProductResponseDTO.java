package br.com.mysterious.mysteriousapi.presentation.dtos.response;

public record ProductResponseDTO(
        Long id,
        String name,
        int quantity,
        Double price,
        String description
){
}
