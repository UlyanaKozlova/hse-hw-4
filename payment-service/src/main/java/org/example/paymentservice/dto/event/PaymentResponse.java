package org.example.paymentservice.dto.event;

public record PaymentResponse(Long orderId, boolean isSuccessful) {
}
