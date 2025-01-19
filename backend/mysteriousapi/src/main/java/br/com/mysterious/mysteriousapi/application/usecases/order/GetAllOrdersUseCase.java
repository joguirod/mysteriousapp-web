package br.com.mysterious.mysteriousapi.application.usecases.order;

import br.com.mysterious.mysteriousapi.domain.entities.order.MysteriousOrder;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousOrderRepository;

import java.util.List;

public class GetAllOrdersUseCase {
    private final MysteriousOrderRepository mysteriousOrderRepository;

    public GetAllOrdersUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        this.mysteriousOrderRepository = mysteriousOrderRepository;
    }

    public List<MysteriousOrder> execute() {
        return mysteriousOrderRepository.findAll();
    }
}
