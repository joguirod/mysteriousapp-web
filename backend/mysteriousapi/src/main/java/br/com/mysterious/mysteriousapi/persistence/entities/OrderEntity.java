package br.com.mysterious.mysteriousapi.persistence.entities;

import br.com.mysterious.mysteriousapi.domain.entities.cart.Cart;
import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousCustomer;
import br.com.mysterious.mysteriousapi.domain.entities.order.OrderStatus;
import br.com.mysterious.mysteriousapi.domain.entities.order.PickUpLocation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderId;
    private LocalDateTime orderDate;
    @OneToOne
    private CartEntity cart;
    @ManyToOne
    private MysteriousCustomerEntity mysteriousCustomer;
    private OrderStatus orderStatus;
    @ManyToOne
    private PickUpLocationEntity pickUpLocation;
}
