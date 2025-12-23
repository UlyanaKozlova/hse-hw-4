package org.example.paymentservice.dto.payment;

public record PaymentRequest(Long orderId, Long userId, Long amount) {
}
