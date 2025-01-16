package br.com.mysterious.mysteriousapi.presentation.dtos.response;

import br.com.mysterious.mysteriousapi.domain.entities.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponseDTO (
    UUID orderId,
    UUID customerId,
    LocalDateTime orderDate,
    LocalDateTime finishDate,
    OrderStatus status,
    Double totalValue,
    List<OrderItemResponseDTO> items,
    List<OrderActionResponseDTO> actions
){
}
