package org.example.orderservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.orderservice.entity.Status;

public record OrderResponse(
        @Schema(description = "Order unique Id")
        Long id,
        @Schema(description = "User Id")
        Long userId,
        @Schema(description = "Price of order")
        Long amount,
        @Schema(description = "Description of order")
        String description,
        // todo опциональное
        @Schema(description = "Status of order: NEW, FINISHED, CANCELLED")
        Status status) {
}
