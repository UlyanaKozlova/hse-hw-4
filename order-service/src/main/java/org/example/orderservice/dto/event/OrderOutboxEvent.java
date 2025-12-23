package org.example.orderservice.dto.event;

public record OrderOutboxEvent(Long orderId, Long userId, Long amount) {
}
