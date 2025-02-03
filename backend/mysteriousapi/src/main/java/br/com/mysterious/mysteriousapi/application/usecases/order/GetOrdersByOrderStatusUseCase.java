package br.com.mysterious.mysteriousapi.application.usecases.order;

import br.com.mysterious.mysteriousapi.domain.entities.order.MysteriousOrder;
import br.com.mysterious.mysteriousapi.domain.entities.order.OrderStatus;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousOrderRepository;

import java.util.ArrayList;
import java.util.List;

public class GetOrdersByOrderStatusUseCase {
    private final MysteriousOrderRepository mysteriousOrderRepository;


    public GetOrdersByOrderStatusUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        this.mysteriousOrderRepository = mysteriousOrderRepository;
    }

    // TODO: refatorar para comparar o status guardado no banco (precisa ajustar no app primeiro)
    public List<MysteriousOrder> execute(OrderStatus orderStatus) {
        if (orderStatus.equals(OrderStatus.FINISHED)) {
            return mysteriousOrderRepository.findAll()
                    .stream().filter(order -> order.getFinishDate() != null)
                    .toList();
        }

        return mysteriousOrderRepository.findAll()
                .stream().filter(order -> order.getFinishDate() == null)
                .toList();
    }
}
