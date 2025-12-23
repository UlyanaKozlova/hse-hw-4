package org.example.paymentservice.util.exception.exceptions;

public class AccountMappingException extends RuntimeException {
    public AccountMappingException() {
        super("Error while mapping payment to payment response");
    }
}
