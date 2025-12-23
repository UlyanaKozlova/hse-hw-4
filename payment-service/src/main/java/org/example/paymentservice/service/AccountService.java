package org.example.paymentservice.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.paymentservice.dto.account.AccountResponse;
import org.example.paymentservice.entity.account.Account;
import org.example.paymentservice.entity.account.AccountMapper;
import org.example.paymentservice.repository.AccountRepository;
import org.example.paymentservice.util.exception.exceptions.AccountAlreadyExistException;
import org.example.paymentservice.util.exception.exceptions.AccountNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;

    public AccountResponse addAccount(Long userId) {
        if (accountRepository.existsByUserId(userId)) {
            throw new AccountAlreadyExistException(userId);
        }
        return accountMapper.accountToResponse(accountRepository.save(new Account(userId)));
    }

    public AccountResponse topUpAccount(Long id, Long sum) {
        if (!accountRepository.existsById(id)) {
            throw new AccountNotFoundException(id);
        }
        if (sum <= 0) {
            throw new IllegalArgumentException("Sum must be more than 0");
        }
        Account account = accountRepository.findAccountById(id);
        account.setBalance(account.getBalance() + sum);
        return accountMapper.accountToResponse(accountRepository.save(account));
    }

    public Long getBalance(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new AccountNotFoundException(id);
        }
        return accountRepository.findAccountById(id).getBalance();
    }
}

