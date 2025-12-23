package org.example.paymentservice.entity.payment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {
    @Id
    @Column(name = "order_id")
    Long orderId;
    @Column(name = "user_id")
    Long userId;

    @Column(nullable = false, name = "amount")
    Long amount;

    @Column(nullable = false, name = "is_successful")
    boolean isSuccessful;

    @Column(nullable = false, name = "processed_at")
    LocalDateTime processedAt;
}
