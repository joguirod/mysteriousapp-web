package br.com.mysterious.mysteriousapi.presentation.dtos.request;

import java.util.List;
import java.util.UUID;

public record OrderRequestDTO (
        UUID mysteriousCustomerId,
        List<OrderItemRequestDTO> items
){
}
