package br.com.mysterious.mysteriousapi.application.usecases.order;

import br.com.mysterious.mysteriousapi.application.exceptions.OrderNotFoundException;
import br.com.mysterious.mysteriousapi.domain.entities.order.Order;
import br.com.mysterious.mysteriousapi.persistence.repositories.OrderRepository;

import java.util.Optional;
import java.util.UUID;

public class GetOrderByIdUseCase {

    private final OrderRepository orderRepository;

    public GetOrderByIdUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(UUID id) throws OrderNotFoundException {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isEmpty()) {
            throw new OrderNotFoundException("Order with the given ID not found");
        }

        return optionalOrder.get();
    }
}
