package br.com.mysterious.mysteriousapi.application.usecases.order;

import br.com.mysterious.mysteriousapi.domain.entities.order.MysteriousOrder;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousOrderRepository;

import java.util.List;

public class GetOrdersByMonthYearUseCase {

    private final MysteriousOrderRepository mysteriousOrderRepository;

    public GetOrdersByMonthYearUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        this.mysteriousOrderRepository = mysteriousOrderRepository;
    }

    public List<MysteriousOrder> execute(Integer month, String year) {
        return this.mysteriousOrderRepository.findByMonthAndYear(month, year);
    }
}
