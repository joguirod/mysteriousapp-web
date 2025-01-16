package br.com.mysterious.mysteriousapi.application.usecases.order;

import br.com.mysterious.mysteriousapi.domain.entities.order.Order;
import br.com.mysterious.mysteriousapi.domain.entities.order.OrderStatus;
import br.com.mysterious.mysteriousapi.persistence.repositories.OrderRepository;

import java.util.List;

public class GetOrdersByOrderStatusUseCase {
    private final OrderRepository orderRepository;


    public GetOrdersByOrderStatusUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> execute(OrderStatus orderStatus) {
        return orderRepository.findByOrderStatus(orderStatus);
    }
}
