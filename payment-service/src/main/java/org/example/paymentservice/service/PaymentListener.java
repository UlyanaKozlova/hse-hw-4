package org.example.paymentservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import lombok.experimental.FieldDefaults;
import org.example.paymentservice.dto.payment.PaymentRequest;
import org.example.paymentservice.entity.payment.Payment;
import org.example.paymentservice.entity.account.Account;
import org.example.paymentservice.entity.payment.PaymentMapper;
import org.example.paymentservice.repository.AccountRepository;
import org.example.paymentservice.repository.PaymentRepository;
import org.example.paymentservice.util.exception.exceptions.AccountNotFoundException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentListener {
    PaymentPublisher paymentPublisher;
    ObjectMapper objectMapper;
    AccountRepository accountRepository;
    PaymentRepository paymentRepository;
    PaymentMapper paymentMapper;

    @RabbitListener(queues = "order.created.queue")
    @Transactional
    public void handle(String message) throws Exception {
        PaymentRequest paymentRequest = objectMapper.readValue(message, PaymentRequest.class);
        if (!paymentRepository.existsByOrderId(paymentRequest.orderId())) {
            if (!accountRepository.existsByUserId(paymentRequest.userId())) {
                throw new AccountNotFoundException(paymentRequest.userId());
            }
            Account account = accountRepository.findByUserId(paymentRequest.userId());
            Payment payment = paymentMapper.paymentRequestToPayment(paymentRequest);
            payment.setProcessedAt(LocalDateTime.now());

            if (account.getBalance() >= paymentRequest.amount()) {
                account.setBalance(account.getBalance() - paymentRequest.amount());
                payment.setSuccessful(true);
            } else {
                payment.setSuccessful(false);
            }
            paymentRepository.save(payment);
            paymentPublisher.sendResult(payment);
        }
    }
}