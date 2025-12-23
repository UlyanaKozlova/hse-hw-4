package org.example.paymentservice.dto.event;

public record PaymentRequest(Long orderId, Long userId, Long amount) {
}
