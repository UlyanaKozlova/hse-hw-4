package org.example.paymentservice.service;

import lombok.AllArgsConstructor;
import org.example.paymentservice.dto.AccountResponse;
import org.example.paymentservice.entity.Account;
import org.example.paymentservice.entity.AccountMapper;
import org.example.paymentservice.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;

    public AccountResponse addAccount(Long userId) {
        if (accountRepository.existsByUserId(userId)) {
            throw new RuntimeException("User already exists");
        }
        return accountMapper.accountToResponse(accountRepository.save(new Account(userId)));
    }

    public AccountResponse topUpAccount(Long id, Long sum) {
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Account do not exist by id");
        }
        Account account = accountRepository.findAccountById(id);
        account.setBalance(account.getBalance() + sum);
        return accountMapper.accountToResponse(accountRepository.save(account));
    }

    public Long getBalance(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Account do not exist by id");
        }
        return accountRepository.findAccountById(id).getBalance();
    }
}

