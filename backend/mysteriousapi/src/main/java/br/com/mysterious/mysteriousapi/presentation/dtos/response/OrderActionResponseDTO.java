package br.com.mysterious.mysteriousapi.presentation.dtos.response;

import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import br.com.mysterious.mysteriousapi.domain.entities.orderAction.OrderActionType;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderActionResponseDTO (
        Long actionId,
        LocalDateTime actionDate,
        String description,
        OrderActionType actionType,
        UUID userId,
        String username
){
}
