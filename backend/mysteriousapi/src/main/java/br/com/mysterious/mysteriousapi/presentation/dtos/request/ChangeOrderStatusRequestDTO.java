package br.com.mysterious.mysteriousapi.presentation.dtos.request;

import java.util.UUID;

public record ChangeOrderStatusRequestDTO(
    UUID orderId,
    UUID userId
){
}
