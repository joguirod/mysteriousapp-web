package br.com.mysterious.mysteriousapi.persistence.entities;

import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import br.com.mysterious.mysteriousapi.domain.entities.order.Order;
import br.com.mysterious.mysteriousapi.domain.entities.orderAction.OrderActionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class OrderActionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderActionId;
    private LocalDateTime actionDate;
    private String description;
    private OrderActionType actionType;
    @ManyToOne
    private OrderEntity order;
    @ManyToOne
    private MysteriousUserEntity mysteriousUser;
}
