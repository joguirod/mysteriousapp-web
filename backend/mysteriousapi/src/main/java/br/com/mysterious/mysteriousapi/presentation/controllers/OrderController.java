package br.com.mysterious.mysteriousapi.presentation.controllers;

import br.com.mysterious.mysteriousapi.application.exceptions.MysteriousCustomerNotFoundException;
import br.com.mysterious.mysteriousapi.application.exceptions.OrderNotFoundException;
import br.com.mysterious.mysteriousapi.application.exceptions.OrderWithoutProductsException;
import br.com.mysterious.mysteriousapi.application.usecases.order.CreateOrderUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.order.GetAllOrdersUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.order.GetOrderByIdUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.order.GetOrdersByCustomerIdUseCase;
import br.com.mysterious.mysteriousapi.domain.entities.order.Order;
import br.com.mysterious.mysteriousapi.presentation.dtos.mappers.OrderDTOMapper;
import br.com.mysterious.mysteriousapi.presentation.dtos.request.OrderRequestDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.OrderResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderDTOMapper orderDTOMapper;
    private final CreateOrderUseCase createOrderUseCase;
    private final GetAllOrdersUseCase getAllOrdersUseCase;
    private final GetOrderByIdUseCase getOrderByIdUseCase;
    private final GetOrdersByCustomerIdUseCase getOrdersByCustomerIdUseCase;

    public OrderController(OrderDTOMapper orderDTOMapper, CreateOrderUseCase createOrderUseCase, GetAllOrdersUseCase getAllOrdersUseCase, GetOrderByIdUseCase getOrderByIdUseCase, GetOrdersByCustomerIdUseCase getOrdersByCustomerIdUseCase) {
        this.orderDTOMapper = orderDTOMapper;
        this.createOrderUseCase = createOrderUseCase;
        this.getAllOrdersUseCase = getAllOrdersUseCase;
        this.getOrderByIdUseCase = getOrderByIdUseCase;
        this.getOrdersByCustomerIdUseCase = getOrdersByCustomerIdUseCase;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) throws OrderWithoutProductsException, MysteriousCustomerNotFoundException {
        Order order = orderDTOMapper.toDomainEntity(orderRequestDTO);
        createOrderUseCase.execute(order);
        return new ResponseEntity<>(orderDTOMapper.toResponseDTO(order), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<Order> orders = getAllOrdersUseCase.execute();
        return new ResponseEntity<>(orderDTOMapper.toResponseDTOList(orders), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable UUID orderId) throws OrderNotFoundException {
        Order order = getOrderByIdUseCase.execute(orderId);
        return new ResponseEntity<>(orderDTOMapper.toResponseDTO(order), HttpStatus.OK);
    }

    @GetMapping("/customer/{idCustomer}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByCustomerId(@PathVariable UUID idCustomer) throws OrderNotFoundException {
        List<Order> orders = getOrdersByCustomerIdUseCase.execute(idCustomer);
        return new ResponseEntity<>(orderDTOMapper.toResponseDTOList(orders), HttpStatus.OK);
    }
}
