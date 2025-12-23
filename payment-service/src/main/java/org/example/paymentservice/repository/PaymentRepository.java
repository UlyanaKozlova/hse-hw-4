package org.example.paymentservice.repository;

import org.example.paymentservice.entity.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
    boolean existsByOrderId(Long orderId);
}