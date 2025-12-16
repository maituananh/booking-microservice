package org.booking.infra.mapper;

import java.time.Instant;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.booking.domain.entity.Order;
import org.booking.infra.entity.OrderEntity;
import org.booking.infra.message.dto.CreateOrderEvent;
import org.booking.infra.message.dto.CreateOrderFailEvent;
import org.booking.infra.message.dto.CreateOrderSuccessEvent;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderInfraMapper {

  public static OrderEntity toEntity(final Order order) {
    return OrderEntity.builder()
        .id(order.getId())
        .status(order.getStatus())
        .productId(order.getProductId())
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  public static Order toDomain(final OrderEntity orderEntity) {
    return Order.builder()
        .id(orderEntity.getId())
        .status(orderEntity.getStatus())
        .productId(orderEntity.getProductId())
        .createdAt(orderEntity.getCreatedAt())
        .updatedAt(orderEntity.getUpdatedAt())
        .build();
  }

  public static Order toDomain(
      final CreateOrderEvent event, final UUID eventId, final UUID traceId) {
    return Order.builder()
        .status(event.getStatus())
        .productId(event.getProductId())
        .quantity(event.getQuantity())
        .eventId(eventId)
        .traceId(traceId)
        .build();
  }

  public static Order toDomain(
      final CreateOrderFailEvent event, final UUID eventId, final UUID traceId) {
    return Order.builder()
        .id(event.getOrderId())
        .status(event.getStatus())
        .productId(event.getProductId())
        .eventId(eventId)
        .traceId(traceId)
        .build();
  }

  public static Order toDomain(
      final CreateOrderSuccessEvent event, final UUID eventId, final UUID traceId) {
    return Order.builder()
        .id(event.getOrderId())
        .status(event.getStatus())
        .productId(event.getProductId())
        .eventId(eventId)
        .traceId(traceId)
        .build();
  }
}
