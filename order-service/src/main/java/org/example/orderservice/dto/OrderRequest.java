package org.example.orderservice.dto;

public record OrderRequest(Long userId, Long amount, String description) {
}
