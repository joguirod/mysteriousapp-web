package br.com.mysterious.mysteriousapi.domain.entities.orderAction;

import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import br.com.mysterious.mysteriousapi.domain.entities.order.MysteriousOrder;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "acaopedido")
@EqualsAndHashCode
@ToString
public class OrderAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_acao_pedido")
    private Long orderActionId;
    @Column(name = "data_acao")
    private LocalDateTime actionDate;
    @Column(name = "descricao")
    private String description;
    @Column(name = "id_tipo_acao")
    private OrderActionType actionType;
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private MysteriousOrder mysteriousOrder;
    @ManyToOne
    @JoinColumn(name = "id_mysterious_user")
    private MysteriousUser mysteriousUser;

    public OrderAction() {
    }

    public OrderAction(Long orderActionId, LocalDateTime actionDate, String description, OrderActionType actionType, MysteriousOrder mysteriousOrder, MysteriousUser mysteriousUser) {
        this.orderActionId = orderActionId;
        this.actionDate = actionDate;
        this.description = description;
        this.actionType = actionType;
        this.mysteriousOrder = mysteriousOrder;
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

    public MysteriousOrder getMysteriousOrder() {
        return mysteriousOrder;
    }

    public void setMysteriousOrder(MysteriousOrder mysteriousOrder) {
        this.mysteriousOrder = mysteriousOrder;
    }

    public MysteriousUser getMysteriousUser() {
        return mysteriousUser;
    }

    public void setMysteriousUser(MysteriousUser mysteriousUser) {
        this.mysteriousUser = mysteriousUser;
    }
}
