package org.example.paymentservice.util.exception;

import org.example.paymentservice.util.exception.exceptions.AccountAlreadyExistException;
import org.example.paymentservice.util.exception.exceptions.AccountMappingException;
import org.example.paymentservice.util.exception.exceptions.AccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AccountExceptionHandler {
    @ExceptionHandler(AccountAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handleAccountAlreadyExistException(AccountAlreadyExistException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleAccountNotFoundException(AccountNotFoundException e) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(AccountMappingException.class)
    public ResponseEntity<Map<String, Object>> handleAccountMappingException(AccountMappingException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", message);
        return ResponseEntity.status(status).body(body);
    }
}
