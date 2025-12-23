package org.example.paymentservice.util.exception.exceptions;

public class AccountAlreadyExistException extends RuntimeException {
    public AccountAlreadyExistException(Long id) {
        super("User with id" + id + " already exists");
    }
}
