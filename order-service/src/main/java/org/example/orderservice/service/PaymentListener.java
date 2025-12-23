package org.example.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.orderservice.dto.event.OrderInboxEvent;
import org.example.orderservice.entity.event.EventMapper;
import org.example.orderservice.entity.order.Order;
import org.example.orderservice.entity.order.Status;
import org.example.orderservice.repository.OrderRepository;
import org.example.orderservice.util.exception.exceptions.OrderNotFoundException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentListener {
    OrderRepository orderRepository;
    ObjectMapper objectMapper;
    EventMapper eventMapper;

    @RabbitListener(queues = "payment.result.queue")
    @Transactional
    public void handle(String message) {
        OrderInboxEvent event = eventMapper.payloadToOrderInboxEvent(message, objectMapper);
        if (!orderRepository.existsById(event.orderId())) {
            throw new OrderNotFoundException(event.orderId());
        }
        Order order = orderRepository.findOrderById(event.orderId());
        order.setStatus(event.isSuccessful() ? Status.FINISHED : Status.CANCELLED);
    }
}
