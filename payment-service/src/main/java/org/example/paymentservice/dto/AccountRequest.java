package org.example.paymentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AccountRequest(
        @Schema(description = "User, userId corresponds to one account")
        Long userId) {
}
