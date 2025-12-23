package org.example.paymentservice.repository;

import org.example.paymentservice.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByUserId(Long userId);

    Account findAccountById(Long id);

    Account findByUserId(Long userId);
}
