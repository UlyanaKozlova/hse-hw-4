package org.example.orderservice.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.orderservice.entity.order.Status;

public record OrderResponse(
        @Schema(description = "Order unique Id")
        Long id,
        @Schema(description = "User Id")
        Long userId,
        @Schema(description = "Price of order")
        Long amount,
        @Schema(description = "Description of order")
        String description,
        @Schema(description = "Status of order: NEW, FINISHED, CANCELLED")
        Status status) {
}
