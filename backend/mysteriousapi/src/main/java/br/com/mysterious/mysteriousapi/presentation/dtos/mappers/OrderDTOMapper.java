package br.com.mysterious.mysteriousapi.presentation.dtos.mappers;

import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousCustomer;
import br.com.mysterious.mysteriousapi.domain.entities.order.Order;
import br.com.mysterious.mysteriousapi.domain.entities.order.OrderItem;
import br.com.mysterious.mysteriousapi.domain.entities.orderAction.OrderAction;
import br.com.mysterious.mysteriousapi.domain.entities.product.Product;
import br.com.mysterious.mysteriousapi.presentation.dtos.request.OrderItemRequestDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.request.OrderRequestDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.OrderActionResponseDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.OrderItemResponseDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.OrderResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDTOMapper {
    public Order toDomainEntity(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        order.setMysteriousCustomer(new MysteriousCustomer(orderRequestDTO.mysteriousCustomerId()));
        order.setOrderItems(orderRequestDTO.items()
                .stream()
                .map(orderItemRequestDTO -> this.orderItemRequestDtoToEntity(orderItemRequestDTO))
                .collect(Collectors.toList()));
        return order;
    }

    public OrderResponseDTO toResponseDTO(Order order) {
        List<OrderItemResponseDTO> orderItemResponseDTOList = order.getOrderItems()
                .stream()
                .map(orderItem -> this.orderItemEntityToResponseDTO(orderItem))
                .collect(Collectors.toList());

        List<OrderActionResponseDTO> orderActionResponseDTOList = order.getOrderActionList()
                .stream()
                .map(orderAction -> this.orderActionToResponseDTO(orderAction))
                .collect(Collectors.toList());

        return new OrderResponseDTO(
            order.getOrderId(),
            order.getMysteriousCustomer().getMysteriousCustomerId(),
            order.getOrderDate(),
            order.getFinishDate(),
            order.getOrderStatus(),
            order.getTotalValue(),
            orderItemResponseDTOList,
            orderActionResponseDTOList
        );
    }

    public List<OrderResponseDTO> toResponseDTOList(List<Order> orders) {
        return orders.stream()
                .map(this::toResponseDTO).
                collect(Collectors.toList());
    }

    private OrderItem orderItemRequestDtoToEntity(OrderItemRequestDTO orderItemRequestDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(new Product(orderItemRequestDTO.productId()));
        orderItem.setQuantity(orderItem.getQuantity());
        orderItem.setPrice(orderItem.getPrice());
        return orderItem;
    }

    private OrderItemResponseDTO orderItemEntityToResponseDTO(OrderItem orderItem) {
        return new OrderItemResponseDTO(
            orderItem.getOrderItemId(),
            orderItem.getProduct().getProductId(),
            orderItem.getQuantity(),
            orderItem.getPrice()
        );
    }

    private OrderActionResponseDTO orderActionToResponseDTO(OrderAction orderAction) {
        return new OrderActionResponseDTO(
            orderAction.getOrderActionId(),
            orderAction.getActionDate(),
            orderAction.getDescription(),
            orderAction.getActionType(),
            orderAction.getMysteriousUser().getMysteriousUserId(),
            orderAction.getMysteriousUser().getUsername()
        );
    }
}
