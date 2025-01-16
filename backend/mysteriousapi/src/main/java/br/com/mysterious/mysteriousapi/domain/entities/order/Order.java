package br.com.mysterious.mysteriousapi.domain.entities.order;

import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousCustomer;
import br.com.mysterious.mysteriousapi.domain.entities.orderAction.OrderAction;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderId;
    private LocalDateTime orderDate;
    private LocalDateTime finishDate;
    @ManyToOne
    private MysteriousCustomer mysteriousCustomer;
    private OrderStatus orderStatus;
    @ManyToOne
    private PickUpLocation pickUpLocation;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    @OneToMany(fetch = FetchType.LAZY)
    private List<OrderAction> orderActionList;
    private Double totalValue;

    public Order() {
        this.orderItems = new ArrayList<>();
        this.orderActionList = new ArrayList<>();
    }

    public Order(UUID orderId, LocalDateTime orderDate, MysteriousCustomer mysteriousCustomer, OrderStatus orderStatus, PickUpLocation pickUpLocation, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.mysteriousCustomer = mysteriousCustomer;
        this.orderStatus = orderStatus;
        this.pickUpLocation = pickUpLocation;
        this.orderItems = orderItems;
    }

    public Double calculateTotalValue() {
        Double total = 0.0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.getPrice();
        }
        return total;
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

    public MysteriousCustomer getMysteriousCustomer() {
        return mysteriousCustomer;
    }

    public void setMysteriousCustomer(MysteriousCustomer mysteriousCustomer) {
        this.mysteriousCustomer = mysteriousCustomer;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PickUpLocation getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(PickUpLocation pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
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
