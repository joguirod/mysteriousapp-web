package br.com.mysterious.mysteriousapi.application.usecases.order;

import br.com.mysterious.mysteriousapi.domain.entities.order.MysteriousOrder;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousOrderRepository;

import java.util.List;
import java.util.UUID;

public class GetOrdersByCustomerIdUseCase {

    private final MysteriousOrderRepository mysteriousOrderRepository;

    public GetOrdersByCustomerIdUseCase(MysteriousOrderRepository mysteriousOrderRepository) {
        this.mysteriousOrderRepository = mysteriousOrderRepository;
    }

    public List<MysteriousOrder> execute(UUID customerId) {
        return mysteriousOrderRepository.findByMysteriousCustomer_MysteriousCustomerId(customerId);
    }
}
