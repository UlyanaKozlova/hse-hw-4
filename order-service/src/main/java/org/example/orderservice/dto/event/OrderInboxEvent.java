package org.example.orderservice.dto.event;

public record OrderInboxEvent(Long orderId, boolean isSuccessful) {
}
