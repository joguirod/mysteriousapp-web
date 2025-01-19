package br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser;

import br.com.mysterious.mysteriousapi.application.exceptions.MysteriousUserNotFoundException;
import br.com.mysterious.mysteriousapi.application.exceptions.OrderNotFoundException;
import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import br.com.mysterious.mysteriousapi.domain.entities.order.MysteriousOrder;
import br.com.mysterious.mysteriousapi.domain.entities.order.OrderStatus;
import br.com.mysterious.mysteriousapi.domain.entities.orderAction.OrderAction;
import br.com.mysterious.mysteriousapi.domain.entities.orderAction.OrderActionType;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousOrderRepository;
import br.com.mysterious.mysteriousapi.persistence.repositories.MysteriousUserRepository;
import br.com.mysterious.mysteriousapi.persistence.repositories.OrderActionRepository;

import java.util.Optional;
import java.util.UUID;

public class FinishOrderUseCase {

    private final MysteriousOrderRepository mysteriousOrderRepository;
    private final MysteriousUserRepository mysteriousUserRepository;

    public FinishOrderUseCase(MysteriousOrderRepository mysteriousOrderRepository, MysteriousUserRepository mysteriousUserRepository) {
        this.mysteriousOrderRepository = mysteriousOrderRepository;
        this.mysteriousUserRepository = mysteriousUserRepository;
    }

    public MysteriousOrder execute(UUID userId, UUID orderId) throws OrderNotFoundException, MysteriousUserNotFoundException {
        Optional<MysteriousOrder>  mysteriousOrderTemp = mysteriousOrderRepository.findById(orderId);
        if (mysteriousOrderTemp.isEmpty()) {
            throw new OrderNotFoundException("Order with the given ID not found");
        }

        Optional<MysteriousUser> mysteriousUser = mysteriousUserRepository.findById(userId);
        if (mysteriousUser.isEmpty()) {
            throw new MysteriousUserNotFoundException("Mysterious User with the given ID not found");
        }

        MysteriousOrder mysteriousOrder = mysteriousOrderTemp.get();
        mysteriousOrder.finishOrder();

        OrderAction orderAction = new OrderAction();
        orderAction.setMysteriousOrder(mysteriousOrder);
        orderAction.setActionDate(mysteriousOrder.getFinishDate());
        orderAction.setActionType(OrderActionType.FINISH);
        orderAction.setMysteriousUser(mysteriousUser.get());

        mysteriousOrder.addOrderAction(orderAction);
        return mysteriousOrderRepository.save(mysteriousOrder);
    }
}
