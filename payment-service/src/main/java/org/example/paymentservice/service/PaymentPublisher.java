package org.example.paymentservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.paymentservice.dto.event.PaymentResponse;
import org.example.paymentservice.entity.payment.Payment;
import org.example.paymentservice.entity.payment.PaymentMapper;
import org.example.paymentservice.util.exception.exceptions.AccountMappingException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentPublisher {
    RabbitTemplate rabbitTemplate;
    ObjectMapper objectMapper;
    PaymentMapper paymentMapper;

    public void sendResult(Payment payment) {
        try {
            PaymentResponse paymentResponse = paymentMapper.paymentToPaymentResponse(payment);
            rabbitTemplate.convertAndSend(
                    "payment.exchange",
                    "payment.result",
                    objectMapper.writeValueAsString(paymentResponse));
        } catch (JsonProcessingException e) {
            throw new AccountMappingException();
        }
    }
}