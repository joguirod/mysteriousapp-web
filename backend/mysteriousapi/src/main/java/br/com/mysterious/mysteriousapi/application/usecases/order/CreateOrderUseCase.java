package br.com.mysterious.mysteriousapi.application.usecases.order;

import br.com.mysterious.mysteriousapi.application.exceptions.MysteriousCustomerNotFoundException;
import br.com.mysterious.mysteriousapi.application.exceptions.OrderWithoutProductsException;
import br.com.mysterious.mysteriousapi.domain.entities.order.Order;
import br.com.mysterious.mysteriousapi.domain.entities.order.OrderStatus;
import br.com.mysterious.mysteriousapi.domain.entities.orderAction.OrderAction;
import br.com.mysterious.mysteriousapi.domain.entities.orderAction.OrderActionType;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousCustomerRepository;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousUserRepository;
import br.com.mysterious.mysteriousapi.persistence.repositories.OrderActionRepository;
import br.com.mysterious.mysteriousapi.persistence.repositories.OrderRepository;

import java.time.LocalDateTime;

public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final MysteriousCustomerRepository mysteriousCustomerRepository;
    private final OrderActionRepository orderActionRepository;

    public CreateOrderUseCase(OrderRepository orderRepository, MysteriousCustomerRepository mysteriousCustomerRepository, OrderActionRepository orderActionRepository) {
        this.orderRepository = orderRepository;
        this.mysteriousCustomerRepository = mysteriousCustomerRepository;
        this.orderActionRepository = orderActionRepository;
    }

    public Order execute(Order order) throws MysteriousCustomerNotFoundException, OrderWithoutProductsException {
        if (order.getOrderItems().isEmpty()) {
            throw new OrderWithoutProductsException("A order needs at least one product to be concluded");
        }

        if (mysteriousCustomerRepository.findById(order.getMysteriousCustomer().getMysteriousCustomerId()).isEmpty()) {
            throw new MysteriousCustomerNotFoundException("Mysterious costumer with the given ID not found");
        }

        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.ONGOING);

        order = orderRepository.save(order);

        OrderAction orderAction = new OrderAction();
        orderAction.setOrder(order);
        orderAction.setActionDate(order.getOrderDate());
        orderAction.setActionType(OrderActionType.CREATE);
        orderAction.setMysteriousUser(order.getMysteriousCustomer().getMysteriousUser());
        orderActionRepository.save(orderAction);

        return order;
    }
}
