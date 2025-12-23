package org.example.paymentservice.dto.payment;

public record PaymentResponse(Long orderId, boolean isSuccessful) {
}
