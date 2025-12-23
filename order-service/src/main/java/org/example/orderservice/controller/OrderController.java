package org.example.orderservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.example.orderservice.dto.OrderRequest;
import org.example.orderservice.dto.OrderResponse;
import org.example.orderservice.entity.Status;
import org.example.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order-service")
public class OrderController {
    private final OrderService orderService;

    @Operation(description = "Post order by userId, amount, description")
    @PostMapping("/order")
    public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder(orderRequest));
    }

    @Operation(description = "Get all orders")
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrders());
    }

    @Operation(description = "Get order status by id")
    @GetMapping("/{id}/status")
    public ResponseEntity<Status> getStatusById(@Parameter(description = "Order id", example = "1")
                                                @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getStatusById(id));
    }
}
