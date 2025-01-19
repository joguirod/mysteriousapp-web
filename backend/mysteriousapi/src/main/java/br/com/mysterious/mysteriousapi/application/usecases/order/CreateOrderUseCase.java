package br.com.mysterious.mysteriousapi.application.usecases.order;

import br.com.mysterious.mysteriousapi.application.exceptions.MysteriousCustomerNotFoundException;
import br.com.mysterious.mysteriousapi.application.exceptions.OrderWithoutProductsException;
import br.com.mysterious.mysteriousapi.domain.entities.order.MysteriousOrder;
import br.com.mysterious.mysteriousapi.domain.entities.order.OrderStatus;
import br.com.mysterious.mysteriousapi.domain.entities.orderAction.OrderAction;
import br.com.mysterious.mysteriousapi.domain.entities.orderAction.OrderActionType;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousCustomerRepository;
import br.com.mysterious.mysteriousapi.persistence.repositories.OrderActionRepository;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousOrderRepository;

import java.time.LocalDateTime;

public class CreateOrderUseCase {

    private final MysteriousOrderRepository mysteriousOrderRepository;
    private final MysteriousCustomerRepository mysteriousCustomerRepository;

    public CreateOrderUseCase(MysteriousOrderRepository mysteriousOrderRepository, MysteriousCustomerRepository mysteriousCustomerRepository) {
        this.mysteriousOrderRepository = mysteriousOrderRepository;
        this.mysteriousCustomerRepository = mysteriousCustomerRepository;
    }

    public MysteriousOrder execute(MysteriousOrder mysteriousOrder) throws MysteriousCustomerNotFoundException, OrderWithoutProductsException {
        if (mysteriousOrder.getOrderItems().isEmpty()) {
            throw new OrderWithoutProductsException("A order needs at least one product to be concluded");
        }

        if (mysteriousCustomerRepository.findById(mysteriousOrder.getMysteriousCustomer().getMysteriousCustomerId()).isEmpty()) {
            throw new MysteriousCustomerNotFoundException("Mysterious costumer with the given ID not found");
        }

        mysteriousOrder.setOrderDate(LocalDateTime.now());
        mysteriousOrder.setOrderStatus(OrderStatus.ONGOING);

        OrderAction orderAction = new OrderAction();
        orderAction.setMysteriousOrder(mysteriousOrder);
        orderAction.setActionDate(mysteriousOrder.getOrderDate());
        orderAction.setActionType(OrderActionType.CREATE);
        orderAction.setMysteriousUser(mysteriousOrder.getMysteriousCustomer().getMysteriousUser());

        mysteriousOrder.addOrderAction(orderAction);

        return mysteriousOrderRepository.save(mysteriousOrder);
    }
}
