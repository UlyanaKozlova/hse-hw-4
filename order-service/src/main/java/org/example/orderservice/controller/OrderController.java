package org.example.orderservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.orderservice.dto.order.OrderRequest;
import org.example.orderservice.dto.order.OrderResponse;
import org.example.orderservice.entity.order.Status;
import org.example.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order-service")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderService orderService;

    @Operation(description = "Post order by userId, amount, description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Error with deserialization of order objects"),
            @ApiResponse(responseCode = "404", description = "No order by id is found")
    })
    @PostMapping("/order")
    public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderRequest orderRequest) {// todo PathVariable
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder(orderRequest));
    }

    @Operation(description = "Get all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Error with deserialization of order objects"),
            @ApiResponse(responseCode = "404", description = "No orders are found")
    })
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrders());
    }

    @Operation(description = "Get order status by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Error with deserialization of order objects"),
            @ApiResponse(responseCode = "404", description = "No order by id is found")
    })
    @GetMapping("/{id}/status")
    public ResponseEntity<Status> getStatusById(@Parameter(description = "Order id", example = "1")
                                                @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getStatusById(id));
    }
}
