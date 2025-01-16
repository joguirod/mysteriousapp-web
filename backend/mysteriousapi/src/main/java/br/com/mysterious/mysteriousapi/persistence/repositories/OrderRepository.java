package br.com.mysterious.mysteriousapi.persistence.repositories;

import br.com.mysterious.mysteriousapi.domain.entities.order.Order;
import br.com.mysterious.mysteriousapi.domain.entities.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByMysteriousCustomer_MysteriousCustomerId(UUID mysteriousCustomerMysteriousCustomerId);

    List<Order> findByOrderStatus(OrderStatus orderStatus);
}
