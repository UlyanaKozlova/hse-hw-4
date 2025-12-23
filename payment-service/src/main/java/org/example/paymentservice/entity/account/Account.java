package org.example.paymentservice.entity.account;

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
@Table(name = "accounts")
@NoArgsConstructor
public class Account {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    Long balance;
    @Column(name = "user_id")
    Long userId;

    public Account(Long userId) {
        this.userId = userId;
        this.balance = 0L;
    }
}
