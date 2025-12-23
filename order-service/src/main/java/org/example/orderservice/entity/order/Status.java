package org.example.orderservice.entity.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    NEW("new"),
    FINISHED("finished"),
    CANCELLED("cancelled");
    final String value;
}
