package br.com.mysterious.mysteriousapi.application.usecases.order;

import br.com.mysterious.mysteriousapi.domain.entities.order.Order;
import br.com.mysterious.mysteriousapi.persistence.repositories.OrderRepository;

import java.util.List;

public class GetAllOrdersUseCase {
    private final OrderRepository orderRepository;

    public GetAllOrdersUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> execute() {
        return orderRepository.findAll();
    }
}
