package br.com.mysterious.mysteriousapi.presentation.controllers;

import br.com.mysterious.mysteriousapi.application.exceptions.MysteriousCustomerNotFoundException;
import br.com.mysterious.mysteriousapi.application.exceptions.MysteriousUserNotFoundException;
import br.com.mysterious.mysteriousapi.application.exceptions.OrderNotFoundException;
import br.com.mysterious.mysteriousapi.application.exceptions.OrderWithoutProductsException;
import br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser.CancelOrderUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.order.CreateOrderUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.order.GetAllOrdersUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.order.GetOrderByIdUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.order.GetOrdersByCustomerIdUseCase;
import br.com.mysterious.mysteriousapi.domain.entities.order.MysteriousOrder;
import br.com.mysterious.mysteriousapi.presentation.dtos.mappers.MysteriousOrderDTOMapper;
import br.com.mysterious.mysteriousapi.presentation.dtos.request.ChangeOrderStatusRequestDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.request.OrderRequestDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.OrderResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final MysteriousOrderDTOMapper mysteriousOrderDTOMapper;
    private final CreateOrderUseCase createOrderUseCase;
    private final GetAllOrdersUseCase getAllOrdersUseCase;
    private final GetOrderByIdUseCase getOrderByIdUseCase;
    private final GetOrdersByCustomerIdUseCase getOrdersByCustomerIdUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;

    public OrderController(MysteriousOrderDTOMapper mysteriousOrderDTOMapper, CreateOrderUseCase createOrderUseCase, GetAllOrdersUseCase getAllOrdersUseCase, GetOrderByIdUseCase getOrderByIdUseCase, GetOrdersByCustomerIdUseCase getOrdersByCustomerIdUseCase, CancelOrderUseCase cancelOrderUseCase) {
        this.mysteriousOrderDTOMapper = mysteriousOrderDTOMapper;
        this.createOrderUseCase = createOrderUseCase;
        this.getAllOrdersUseCase = getAllOrdersUseCase;
        this.getOrderByIdUseCase = getOrderByIdUseCase;
        this.getOrdersByCustomerIdUseCase = getOrdersByCustomerIdUseCase;
        this.cancelOrderUseCase = cancelOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) throws OrderWithoutProductsException, MysteriousCustomerNotFoundException {
        MysteriousOrder mysteriousOrder = mysteriousOrderDTOMapper.toDomainEntity(orderRequestDTO);
        createOrderUseCase.execute(mysteriousOrder);
        return new ResponseEntity<>(mysteriousOrderDTOMapper.toResponseDTO(mysteriousOrder), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<MysteriousOrder> mysteriousOrders = getAllOrdersUseCase.execute();
        return new ResponseEntity<>(mysteriousOrderDTOMapper.toResponseDTOList(mysteriousOrders), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable UUID orderId) throws OrderNotFoundException {
        MysteriousOrder mysteriousOrder = getOrderByIdUseCase.execute(orderId);
        return new ResponseEntity<>(mysteriousOrderDTOMapper.toResponseDTO(mysteriousOrder), HttpStatus.OK);
    }

    @GetMapping("/customer/{idCustomer}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByCustomerId(@PathVariable UUID idCustomer) throws OrderNotFoundException {
        List<MysteriousOrder> mysteriousOrders = getOrdersByCustomerIdUseCase.execute(idCustomer);
        return new ResponseEntity<>(mysteriousOrderDTOMapper.toResponseDTOList(mysteriousOrders), HttpStatus.OK);
    }

    @PostMapping("/finish")
    public ResponseEntity<OrderResponseDTO> finishOrder(@RequestBody ChangeOrderStatusRequestDTO finishOrderRequestDTO) throws OrderNotFoundException, MysteriousUserNotFoundException {
        MysteriousOrder order = cancelOrderUseCase.execute(finishOrderRequestDTO.userId(), finishOrderRequestDTO.orderId());
        return new ResponseEntity<>(mysteriousOrderDTOMapper.toResponseDTO(order), HttpStatus.OK);
    }

    @PostMapping("/cancel")
    public ResponseEntity<OrderResponseDTO> cancelOrder(@RequestBody ChangeOrderStatusRequestDTO changeOrderStatusRequestDTO) throws OrderNotFoundException, MysteriousUserNotFoundException {
        MysteriousOrder order = cancelOrderUseCase.execute(changeOrderStatusRequestDTO.userId(), changeOrderStatusRequestDTO.orderId());
        return new ResponseEntity<>(mysteriousOrderDTOMapper.toResponseDTO(order), HttpStatus.OK);
    }
}
