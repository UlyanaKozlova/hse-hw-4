package org.example.orderservice.service;

import lombok.AllArgsConstructor;
import org.example.orderservice.dto.OrderRequest;
import org.example.orderservice.dto.OrderResponse;
import org.example.orderservice.entity.Order;
import org.example.orderservice.entity.OrderMapper;
import org.example.orderservice.entity.Status;
import org.example.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderResponse addOrder(OrderRequest orderRequest) {
        Order order = orderMapper.requestToOrder(orderRequest);
        order.setStatus(Status.NEW);

        // todo оплата
        return orderMapper.orderToResponse(orderRepository.save(order));
    }

    public List<OrderResponse> getOrders() {
        return orderRepository.findAll().stream().map(orderMapper::orderToResponse).collect(Collectors.toList());
    }

    public Status getStatusById(Long id) {
        return orderRepository.findStatusById(id); //todo мб в репозитории
    }
}
