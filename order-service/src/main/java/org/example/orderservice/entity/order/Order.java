package org.example.orderservice.entity.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Entity
@FieldDefaults(level = PRIVATE)
@Table(name = "orders")
@NoArgsConstructor
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    @Column(name = "user_id")
    Long userId;
    Long amount;
    String description;
    @Enumerated(EnumType.STRING)
    Status status;

    public Order(Long userId, Long amount, String description, Status status) {
        this.userId = userId;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }
}
