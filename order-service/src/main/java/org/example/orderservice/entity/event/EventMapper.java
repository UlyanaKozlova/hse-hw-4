package org.example.orderservice.entity.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.orderservice.dto.event.OrderInboxEvent;
import org.example.orderservice.dto.event.OrderOutboxEvent;
import org.example.orderservice.util.exception.exceptions.OrderMappingException;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    default OrderEvent orderOutboxEventToOrderEvent(OrderOutboxEvent orderOutboxEvent, ObjectMapper objectMapper) {
        try {
            OrderEvent orderEvent = new OrderEvent();
            orderEvent.setPayload(objectMapper.writeValueAsString(orderOutboxEvent));
            orderEvent.setSent(false);
            return orderEvent;
        } catch (JsonProcessingException e) {
            throw new OrderMappingException("orderOutboxEvent");
        }
    }

    default OrderInboxEvent payloadToOrderInboxEvent(String payload, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(payload, OrderInboxEvent.class);
        } catch (JsonProcessingException e) {
            throw new OrderMappingException("payload");
        }
    }
}
