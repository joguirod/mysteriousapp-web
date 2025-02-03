package br.com.mysterious.mysteriousapi.common.config;

import br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser.CancelOrderUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser.FinishOrderUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.order.*;
import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousCustomerRepository;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousUserRepository;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousOrderRepository;
import br.com.mysterious.mysteriousapi.presentation.dtos.mappers.MysteriousOrderDTOMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MysteriousOrderConfig {
    @Bean
    CreateOrderUseCase createOrderUseCase(MysteriousOrderRepository mysteriousOrderRepository, MysteriousUserRepository mysteriousUserRepository) {
        return new CreateOrderUseCase(mysteriousOrderRepository, mysteriousUserRepository);
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
    GetOrdersByYearUseCase getOrdersByYearUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        return new GetOrdersByYearUseCase(mysteriousOrderRepository);
    }

    @Bean
    GetOrdersByMonthUseCase getOrdersByMonthUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        return new GetOrdersByMonthUseCase(mysteriousOrderRepository);
    }

    @Bean
    GetOrdersByMonthYearUseCase getOrdersByMonthYearUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        return new GetOrdersByMonthYearUseCase(mysteriousOrderRepository);
    }

    @Bean
    GetTotalValueByYearUseCase getTotalValueByYearUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        return new GetTotalValueByYearUseCase(mysteriousOrderRepository);
    }

    @Bean
    GetTotalValueByMonthUseCase getTotalValueByMonthUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        return new GetTotalValueByMonthUseCase(mysteriousOrderRepository);
    }

    @Bean
    GetTotalValueByMonthYearUseCase getTotalValueByMonthYearUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        return new GetTotalValueByMonthYearUseCase(mysteriousOrderRepository);
    }

    @Bean
    MysteriousOrderDTOMapper orderDTOMapper() {
        return new MysteriousOrderDTOMapper();
    }
}
