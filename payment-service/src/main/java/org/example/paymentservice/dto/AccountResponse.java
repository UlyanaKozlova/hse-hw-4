package org.example.paymentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AccountResponse(
        @Schema(description = "Unique account id")
        Long id,
        @Schema(description = "Current account balance")
        Long balance,
        @Schema(description = "User Id, userId corresponds to one account")
        Long userId) {
}
