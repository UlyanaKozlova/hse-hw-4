package org.example.orderservice.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.orderservice.dto.event.OrderOutboxEvent;
import org.example.orderservice.dto.order.OrderResponse;
import org.example.orderservice.entity.event.EventMapper;
import org.example.orderservice.entity.order.Order;
import org.example.orderservice.entity.order.OrderMapper;
import org.example.orderservice.entity.order.Status;
import org.example.orderservice.entity.event.OrderEvent;
import org.example.orderservice.repository.OrderRepository;
import org.example.orderservice.repository.EventRepository;
import org.example.orderservice.util.exception.exceptions.OrderNotFoundException;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    ObjectMapper objectMapper;
    EventRepository eventRepository;
    EventMapper eventMapper;

    @Transactional
    public OrderResponse addOrder(Long userId, Long amount, String description) {
        Order order =new Order(userId, amount,description);
        orderRepository.save(order);
        OrderEvent outbox = eventMapper.orderOutboxEventToOrderEvent(
                new OrderOutboxEvent(order.getId(), order.getUserId(), order.getAmount()),
                objectMapper);
        eventRepository.save(outbox);
        return orderMapper.orderToResponse(order);
    }

    public List<OrderResponse> getOrders() {
        if (orderRepository.count() == 0) {
            throw new OrderNotFoundException();
        }
        return orderRepository.findAll().stream().map(orderMapper::orderToResponse).collect(Collectors.toList());
    }

    public Status getStatusById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException(id);
        }
        return orderRepository.findStatusById(id);
    }
}
