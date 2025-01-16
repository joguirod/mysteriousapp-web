package br.com.mysterious.mysteriousapi.application.usecases.order;

import br.com.mysterious.mysteriousapi.domain.entities.order.Order;
import br.com.mysterious.mysteriousapi.persistence.repositories.OrderRepository;

import java.util.List;
import java.util.UUID;

public class GetOrdersByCustomerIdUseCase {

    private final OrderRepository orderRepository;

    public GetOrdersByCustomerIdUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> execute(UUID customerId) {
        return orderRepository.findByMysteriousCustomer_MysteriousCustomerId(customerId);
    }
}
