package br.com.mysterious.mysteriousapi.application.usecases.order;

import br.com.mysterious.mysteriousapi.domain.entities.order.MysteriousOrder;
import br.com.mysterious.mysteriousapi.domain.entities.order.OrderStatus;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousOrderRepository;

import java.util.List;

public class GetOrdersByOrderStatusUseCase {
    private final MysteriousOrderRepository mysteriousOrderRepository;


    public GetOrdersByOrderStatusUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        this.mysteriousOrderRepository = mysteriousOrderRepository;
    }

    public List<MysteriousOrder> execute(OrderStatus orderStatus) {
        return mysteriousOrderRepository.findByOrderStatus(orderStatus);
    }
}
