package br.com.mysterious.mysteriousapi.persistence.repositories;

import br.com.mysterious.mysteriousapi.domain.entities.order.MysteriousOrder;
import br.com.mysterious.mysteriousapi.domain.entities.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MysteriousOrderRepository extends JpaRepository<MysteriousOrder, UUID> {
    List<MysteriousOrder> findByMysteriousCustomer_MysteriousCustomerId(UUID mysteriousCustomerMysteriousCustomerId);

    List<MysteriousOrder> findByOrderStatus(OrderStatus orderStatus);
}
