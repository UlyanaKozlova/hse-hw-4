package org.example.orderservice.dto;

import org.example.orderservice.entity.Status;

public record OrderResponse(Long id, Long userId, Long amount, String description, Status status) {
}
