package br.com.mysterious.mysteriousapi.application.usecases.order;

import br.com.mysterious.mysteriousapi.domain.entities.order.MysteriousOrder;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousOrderRepository;

import java.util.List;

public class GetOrdersByYearUseCase {

    private final MysteriousOrderRepository mysteriousOrderRepository;

    public GetOrdersByYearUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        this.mysteriousOrderRepository = mysteriousOrderRepository;
    }

    public List<MysteriousOrder> execute(String year) {
        return mysteriousOrderRepository.findByYear(year);
    }
}
