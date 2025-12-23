package org.example.orderservice.util.exception.exceptions;

public class OrderMappingException extends RuntimeException {
    public OrderMappingException(String type) {
        super("Can not deserialize: " + type);
    }
}
