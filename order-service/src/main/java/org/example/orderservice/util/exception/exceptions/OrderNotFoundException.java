package org.example.orderservice.util.exception.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Order with id " + id + " does not exist");
    }

    public OrderNotFoundException() {
        super("Orders repository is empty");
    }
}
