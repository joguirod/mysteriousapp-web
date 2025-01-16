package br.com.mysterious.mysteriousapi.domain.entities.orderAction;

import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import br.com.mysterious.mysteriousapi.domain.entities.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode
@ToString
public class OrderAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderActionId;
    private LocalDateTime actionDate;
    private String description;
    private OrderActionType actionType;
    @ManyToOne
    private Order order;
    @ManyToOne
    private MysteriousUser mysteriousUser;

    public OrderAction() {
    }

    public OrderAction(Long orderActionId, LocalDateTime actionDate, String description, OrderActionType actionType, Order order, MysteriousUser mysteriousUser) {
        this.orderActionId = orderActionId;
        this.actionDate = actionDate;
        this.description = description;
        this.actionType = actionType;
        this.order = order;
        this.mysteriousUser = mysteriousUser;
    }

    public Long getOrderActionId() {
        return orderActionId;
    }

    public void setOrderActionId(Long orderActionId) {
        this.orderActionId = orderActionId;
    }

    public LocalDateTime getActionDate() {
        return actionDate;
    }

    public void setActionDate(LocalDateTime actionDate) {
        this.actionDate = actionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrderActionType getActionType() {
        return actionType;
    }

    public void setActionType(OrderActionType actionType) {
        this.actionType = actionType;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public MysteriousUser getMysteriousUser() {
        return mysteriousUser;
    }

    public void setMysteriousUser(MysteriousUser mysteriousUser) {
        this.mysteriousUser = mysteriousUser;
    }
}
