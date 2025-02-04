package br.com.mysterious.mysteriousapi.domain.entities.order;

import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousCustomer;
import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import br.com.mysterious.mysteriousapi.domain.entities.orderAction.OrderAction;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.hibernate.query.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedido")
public class MysteriousOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id_pedido", columnDefinition = "UUID")
    private UUID orderId;
    @Column(name = "data_pedido")
    private LocalDateTime orderDate;
    @Column(name = "data_finalizacao")
    private LocalDateTime finishDate;
    @ManyToOne
    @JoinColumn(name = "id_mysterious_user")
    private MysteriousUser mysteriousUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderItemId")
    private List<OrderItem> orderItems;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "orderActionId")
    private List<OrderAction> orderActionList;
    @Column(name = "valor_total")
    private Double totalValue;

    public MysteriousOrder() {
        this.orderItems = new ArrayList<>();
        this.orderActionList = new ArrayList<>();
    }

    public MysteriousOrder(UUID orderId, LocalDateTime orderDate, MysteriousUser mysteriousUser, OrderStatus orderStatus, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.mysteriousUser = mysteriousUser;
        this.orderItems = orderItems;
    }

    public Double calculateTotalValue() {
        Double total = 0.0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.getPrice();
        }
        return total;
    }

    public void finishOrder() {
        this.finishDate = LocalDateTime.now();
    }

    public void addOrderAction(OrderAction orderAction) {
        this.orderActionList.add(orderAction);
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public MysteriousUser getMysteriousUser() {
        return mysteriousUser;
    }

    public void setMysteriousUser(MysteriousUser mysteriousUser) {
        this.mysteriousUser = mysteriousUser;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderAction> getOrderActionList() {
        return orderActionList;
    }

    public void setOrderActionList(List<OrderAction> orderActionList) {
        this.orderActionList = orderActionList;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }
}
