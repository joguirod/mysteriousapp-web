package br.com.mysterious.mysteriousapi.presentation.controllers;

import br.com.mysterious.mysteriousapi.application.exceptions.MysteriousCustomerNotFoundException;
import br.com.mysterious.mysteriousapi.application.exceptions.MysteriousUserNotFoundException;
import br.com.mysterious.mysteriousapi.application.exceptions.OrderNotFoundException;
import br.com.mysterious.mysteriousapi.application.exceptions.OrderWithoutProductsException;
import br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser.CancelOrderUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.mysteriousUser.FinishOrderUseCase;
import br.com.mysterious.mysteriousapi.application.usecases.order.*;
import br.com.mysterious.mysteriousapi.domain.entities.order.MysteriousOrder;
import br.com.mysterious.mysteriousapi.domain.entities.order.OrderStatus;
import br.com.mysterious.mysteriousapi.presentation.dtos.mappers.MysteriousOrderDTOMapper;
import br.com.mysterious.mysteriousapi.presentation.dtos.request.ChangeOrderStatusRequestDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.request.OrderRequestDTO;
import br.com.mysterious.mysteriousapi.presentation.dtos.response.OrderResponseDTO;
import jakarta.persistence.criteria.Order;
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
    private final GetOrdersByYearUseCase getOrdersByYearUseCase;
    private final GetOrdersByMonthUseCase getOrdersByMonthUseCase;
    private final GetOrdersByMonthYearUseCase getOrdersByMonthYearUseCase;
    private final GetTotalValueByYearUseCase getTotalValueByYearUseCase;
    private final GetTotalValueByMonthUseCase getTotalValueByMonthUseCase;
    private final GetTotalValueByMonthYearUseCase getTotalValueByMonthYearUseCase;
    private final FinishOrderUseCase finishOrderUseCase;

    public OrderController(MysteriousOrderDTOMapper mysteriousOrderDTOMapper, CreateOrderUseCase createOrderUseCase, GetAllOrdersUseCase getAllOrdersUseCase, GetOrderByIdUseCase getOrderByIdUseCase, GetOrdersByCustomerIdUseCase getOrdersByCustomerIdUseCase, CancelOrderUseCase cancelOrderUseCase, GetOrdersByYearUseCase getOrdersByYearUseCase, GetOrdersByMonthUseCase getOrdersByMonthUseCase, GetOrdersByMonthYearUseCase getOrdersByMonthYearUseCase, GetTotalValueByYearUseCase getTotalValueByYearUseCase, GetTotalValueByMonthUseCase getTotalValueByMonthUseCase, GetTotalValueByMonthYearUseCase getTotalValueByMonthYearUseCase, FinishOrderUseCase finishOrderUseCase) {
        this.mysteriousOrderDTOMapper = mysteriousOrderDTOMapper;
        this.createOrderUseCase = createOrderUseCase;
        this.getAllOrdersUseCase = getAllOrdersUseCase;
        this.getOrderByIdUseCase = getOrderByIdUseCase;
        this.getOrdersByCustomerIdUseCase = getOrdersByCustomerIdUseCase;
        this.cancelOrderUseCase = cancelOrderUseCase;
        this.getOrdersByYearUseCase = getOrdersByYearUseCase;
        this.getOrdersByMonthUseCase = getOrdersByMonthUseCase;
        this.getOrdersByMonthYearUseCase = getOrdersByMonthYearUseCase;
        this.getTotalValueByYearUseCase = getTotalValueByYearUseCase;
        this.getTotalValueByMonthUseCase = getTotalValueByMonthUseCase;
        this.getTotalValueByMonthYearUseCase = getTotalValueByMonthYearUseCase;
        this.finishOrderUseCase = finishOrderUseCase;
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

    @GetMapping("/year/{year}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByYear(@PathVariable String year) {
        List<MysteriousOrder> mysteriousOrders = getOrdersByYearUseCase.execute(year);
        return new ResponseEntity<>(mysteriousOrderDTOMapper.toResponseDTOList(mysteriousOrders), HttpStatus.OK);
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByMonth(@PathVariable Integer month) {
        List<MysteriousOrder> mysteriousOrders = getOrdersByMonthUseCase.execute(month);
        return new ResponseEntity<>(mysteriousOrderDTOMapper.toResponseDTOList(mysteriousOrders), HttpStatus.OK);
    }

    @GetMapping("/epoch")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByEpoch(@RequestParam Integer month, @RequestParam String year) {
        List<MysteriousOrder> mysteriousOrders = getOrdersByMonthYearUseCase.execute(month, year);
        return new ResponseEntity<>(mysteriousOrderDTOMapper.toResponseDTOList(mysteriousOrders), HttpStatus.OK);
    }

    @GetMapping("income/year/{year}")
    public ResponseEntity<Double> getIncomeByYear(@PathVariable String year) {
        return new ResponseEntity<>(getTotalValueByYearUseCase.execute(year), HttpStatus.OK);
    }

    @GetMapping("income/month/{month}")
    public ResponseEntity<Double> getIncomeByMonth(@PathVariable Integer month) {
        return new ResponseEntity<>(getTotalValueByMonthUseCase.execute(month), HttpStatus.OK);
    }

    @GetMapping("income/epoch")
    public ResponseEntity<Double> getIncomeByEpoch(@RequestParam Integer month, @RequestParam String year) {
        return new ResponseEntity<>(getTotalValueByMonthYearUseCase.execute(month, year), HttpStatus.OK);
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
        MysteriousOrder order = finishOrderUseCase.execute(finishOrderRequestDTO.userId(), finishOrderRequestDTO.orderId());
        return new ResponseEntity<>(mysteriousOrderDTOMapper.toResponseDTO(order), HttpStatus.OK);
    }

    @PostMapping("/cancel")
    public ResponseEntity<OrderResponseDTO> cancelOrder(@RequestBody ChangeOrderStatusRequestDTO changeOrderStatusRequestDTO) throws OrderNotFoundException, MysteriousUserNotFoundException {
        MysteriousOrder order = cancelOrderUseCase.execute(changeOrderStatusRequestDTO.userId(), changeOrderStatusRequestDTO.orderId());
        return new ResponseEntity<>(mysteriousOrderDTOMapper.toResponseDTO(order), HttpStatus.OK);
    }
}
