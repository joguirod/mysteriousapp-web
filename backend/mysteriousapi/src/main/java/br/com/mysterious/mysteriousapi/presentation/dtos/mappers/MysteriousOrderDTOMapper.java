package br.com.mysterious.mysteriousapi.presentation.dtos.mappers;

import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousCustomer;
import br.com.mysterious.mysteriousapi.domain.entities.customer.MysteriousUser;
import br.com.mysterious.mysteriousapi.domain.entities.order.MysteriousOrder;
import br.com.mysterious.mysteriousapi.domain.entities.order.OrderItem;
import br.com.mysterious.mysteriousapi.domain.entities.orderAction.OrderAction;
import br.com.mysterious.mysteriousapi.domain.entities.product.Product;
import br.com.mysterious.mysteriousapi.presentation.dtos.request.OrderItemRequestDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.request.OrderRequestDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.*;

import java.util.List;
import java.util.stream.Collectors;

public class MysteriousOrderDTOMapper {
    public MysteriousOrder toDomainEntity(OrderRequestDTO orderRequestDTO) {
        MysteriousOrder mysteriousOrder = new MysteriousOrder();
        mysteriousOrder.setMysteriousUser(new MysteriousUser(orderRequestDTO.mysteriousCustomerId()));
        mysteriousOrder.setOrderItems(orderRequestDTO.items()
                .stream()
                .map(orderItemRequestDTO -> this.orderItemRequestDtoToEntity(orderItemRequestDTO))
                .collect(Collectors.toList()));
        return mysteriousOrder;
    }

    public OrderResponseDTO toResponseDTO(MysteriousOrder mysteriousOrder) {
        List<OrderItemResponseDTO> orderItemResponseDTOList = mysteriousOrder.getOrderItems()
                .stream()
                .map(this::orderItemEntityToResponseDTO)
                .collect(Collectors.toList());

//        List<OrderActionResponseDTO> orderActionResponseDTOList = mysteriousOrder.getOrderActionList()
//                .stream()
//                .map(orderAction -> this.orderActionToResponseDTO(orderAction))
//                .collect(Collectors.toList());

        return new OrderResponseDTO(
            mysteriousOrder.getOrderId(),
            mysteriousOrder.getMysteriousUser().getMysteriousUserId(),
            mysteriousOrder.getOrderDate(),
            mysteriousOrder.getFinishDate(),
            mysteriousOrder.getTotalValue(),
            orderItemResponseDTOList
//            orderActionResponseDTOList
        );
    }

    public List<OrderResponseDTO> toResponseDTOList(List<MysteriousOrder> mysteriousOrders) {
        return mysteriousOrders.stream()
                .map(this::toResponseDTO).
                collect(Collectors.toList());
    }

    private OrderItem orderItemRequestDtoToEntity(OrderItemRequestDTO orderItemRequestDTO) {
        OrderItem orderItem = new OrderItem();
//        orderItem.setProduct(new Product(orderItemRequestDTO.productId()));
        orderItem.setQuantity(orderItem.getQuantity());
        orderItem.setPrice(orderItem.getPrice());
        return orderItem;
    }

    private OrderItemResponseDTO orderItemEntityToResponseDTO(OrderItem orderItem) {
        CategoryResponseDTO category = new CategoryResponseDTO(
                orderItem.getCategory().getId(),
                orderItem.getCategory().getValue(),
                orderItem.getCategory().getDescription()
                );

        GenreResponseDTO genre = new GenreResponseDTO(
                orderItem.getGenre().getId(),
                orderItem.getGenre().getDescription()
        );

        return new OrderItemResponseDTO(
            orderItem.getOrderItemId(),
            category,
            genre,
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
