package org.example.orderservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record OrderRequest(
        @Schema(description = "User Id")
        Long userId,
        @Schema(description = "Price of order")
        Long amount,
        @Schema(description = "Description of order")
        String description) {
}
// todo поменять на price
