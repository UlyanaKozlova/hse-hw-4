package org.example.paymentservice.util.exception.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id) {
        super("Account with id " + id + " does not exist");
    }

    public AccountNotFoundException() {
        super("Account repository is empty!");
    }
}
