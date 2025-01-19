package br.com.mysterious.mysteriousapi.common.config;

import br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser.CancelOrderUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser.FinishOrderUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.order.*;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousCustomerRepository;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousUserRepository;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousOrderRepository;
import br.com.mysterious.mysteriousapi.presentation.dtos.mappers.MysteriousOrderDTOMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MysteriousOrderConfig {
    @Bean
    CreateOrderUseCase createOrderUseCase(MysteriousOrderRepository mysteriousOrderRepository, MysteriousCustomerRepository mysteriousCustomerRepository) {
        return new CreateOrderUseCase(mysteriousOrderRepository, mysteriousCustomerRepository);
    }

    @Bean
    GetAllOrdersUseCase getAllOrdersUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        return new GetAllOrdersUseCase(mysteriousOrderRepository);
    }

    @Bean
    GetOrderByIdUseCase getOrderByIdUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        return new GetOrderByIdUseCase(mysteriousOrderRepository);
    }

    @Bean
    GetOrdersByCustomerIdUseCase getOrdersByCustomerIdUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        return new GetOrdersByCustomerIdUseCase(mysteriousOrderRepository);
    }

    @Bean
    GetOrdersByOrderStatusUseCase getOrdersByOrderStatusUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        return new GetOrdersByOrderStatusUseCase(mysteriousOrderRepository);
    }

    @Bean
    FinishOrderUseCase finishOrderUseCase(MysteriousOrderRepository mysteriousOrderRepository, MysteriousUserRepository mysteriousUserRepository) {
        return new FinishOrderUseCase(mysteriousOrderRepository, mysteriousUserRepository);
    }

    @Bean
    CancelOrderUseCase cancelOrderUseCase(MysteriousOrderRepository mysteriousOrderRepository, MysteriousUserRepository mysteriousUserRepository) {
        return new CancelOrderUseCase(mysteriousOrderRepository, mysteriousUserRepository);
    }

    @Bean
    MysteriousOrderDTOMapper orderDTOMapper() {
        return new MysteriousOrderDTOMapper();
    }
}
