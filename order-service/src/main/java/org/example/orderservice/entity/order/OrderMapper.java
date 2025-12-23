package org.example.orderservice.entity.order;

import org.example.orderservice.dto.order.OrderRequest;
import org.example.orderservice.dto.order.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    Order requestToOrder(OrderRequest orderRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "status", target = "status")
    OrderResponse orderToResponse(Order order);
}
