package org.example.paymentservice.entity.payment;

import org.example.paymentservice.dto.payment.PaymentRequest;
import org.example.paymentservice.dto.payment.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(target = "successful", ignore = true)
    @Mapping(target = "processedAt", ignore = true)
    Payment paymentRequestToPayment(PaymentRequest paymentRequest);
    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "successful", target = "isSuccessful")
    PaymentResponse paymentToPaymentResponse(Payment payment);
}
