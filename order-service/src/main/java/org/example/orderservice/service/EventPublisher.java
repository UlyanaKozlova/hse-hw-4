package org.example.orderservice.service;


import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.orderservice.entity.event.OrderEvent;
import org.example.orderservice.repository.EventRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventPublisher {
    final static int fixedDelay = 3000;
    EventRepository eventRepository;
    RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = fixedDelay)
    @Transactional
    public void publish() {
        List<OrderEvent> events = eventRepository.findBySentFalseOrderByIdAsc();
        for (OrderEvent event : events) {
            rabbitTemplate.convertAndSend("order.exchange", "order.created", event.getPayload());
            event.setSent(true);
        }
    }
}
