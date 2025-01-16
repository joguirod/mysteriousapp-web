package br.com.mysterious.mysteriousapi.common.config;

import br.com.mysterious.mysteriousapi.application.usecases.order.*;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousCustomerRepository;
import br.com.mysterious.mysteriousapi.persistence.repositories.OrderActionRepository;
import br.com.mysterious.mysteriousapi.persistence.repositories.OrderRepository;
import br.com.mysterious.mysteriousapi.presentation.dtos.mappers.OrderDTOMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {
    @Bean
    CreateOrderUseCase createOrderUseCase(OrderRepository orderRepository, MysteriousCustomerRepository mysteriousCustomerRepository, OrderActionRepository orderActionRepository) {
        return new CreateOrderUseCase(orderRepository, mysteriousCustomerRepository, orderActionRepository);
    }

    @Bean
    GetAllOrdersUseCase getAllOrdersUseCase(OrderRepository orderRepository) {
        return new GetAllOrdersUseCase(orderRepository);
    }

    @Bean
    GetOrderByIdUseCase getOrderByIdUseCase(OrderRepository orderRepository) {
        return new GetOrderByIdUseCase(orderRepository);
    }

    @Bean
    GetOrdersByCustomerIdUseCase getOrdersByCustomerIdUseCase(OrderRepository orderRepository) {
        return new GetOrdersByCustomerIdUseCase(orderRepository);
    }

    @Bean
    GetOrdersByOrderStatusUseCase getOrdersByOrderStatusUseCase(OrderRepository orderRepository) {
        return new GetOrdersByOrderStatusUseCase(orderRepository);
    }

    @Bean
    OrderDTOMapper orderDTOMapper() {
        return new OrderDTOMapper();
    }
}
