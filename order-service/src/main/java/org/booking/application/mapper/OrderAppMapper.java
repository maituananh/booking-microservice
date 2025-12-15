package org.booking.application.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.booking.domain.entity.Order;
import org.booking.presentation.request.CreateOrderRequest;
import org.common.type.OrderStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderAppMapper {

  public static Order toDomain(final CreateOrderRequest createOrderRequest) {
    return Order.builder()
        .productId(createOrderRequest.getProductId())
        .status(OrderStatus.PENDING)
        .quantity(createOrderRequest.getQuantity())
        .build();
  }
}
