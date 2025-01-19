package br.com.mysterious.mysteriousapi.application.usecases.order;

import br.com.mysterious.mysteriousapi.application.exceptions.OrderNotFoundException;
import br.com.mysterious.mysteriousapi.domain.entities.order.MysteriousOrder;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousOrderRepository;

import java.util.Optional;
import java.util.UUID;

public class GetOrderByIdUseCase {

    private final MysteriousOrderRepository mysteriousOrderRepository;

    public GetOrderByIdUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        this.mysteriousOrderRepository = mysteriousOrderRepository;
    }

    public MysteriousOrder execute(UUID id) throws OrderNotFoundException {
        Optional<MysteriousOrder> optionalOrder = mysteriousOrderRepository.findById(id);

        if (optionalOrder.isEmpty()) {
            throw new OrderNotFoundException("Order with the given ID not found");
        }

        return optionalOrder.get();
    }
}
