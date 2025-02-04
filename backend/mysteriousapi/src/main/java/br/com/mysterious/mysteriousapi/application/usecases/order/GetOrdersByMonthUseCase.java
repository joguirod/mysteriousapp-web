package br.com.mysterious.mysteriousapi.application.usecases.order;

import br.com.mysterious.mysteriousapi.domain.entities.order.MysteriousOrder;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousOrderRepository;

import java.util.List;

public class GetOrdersByMonthUseCase {

    private final MysteriousOrderRepository mysteriousOrderRepository;


    public GetOrdersByMonthUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        this.mysteriousOrderRepository = mysteriousOrderRepository;
    }

    public List<MysteriousOrder> execute(Integer month) {
        return mysteriousOrderRepository.findByMonth(month);
    }
}
