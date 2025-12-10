package org.booking.application.mapper;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.booking.domain.entity.Order;
import org.booking.domain.entity.OrderOutbox;
import org.booking.share.type.AggregateType;
import org.booking.share.type.OrderType;
import org.booking.share.type.Topic;
import tools.jackson.databind.ObjectMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderOutboxAppMapper {

  public static OrderOutbox toCreateOrder(final Order order, final ObjectMapper objectMapper) {
    final var traceId = UUID.randomUUID();
    final var aggregateId = UUID.randomUUID();

    final var orderOutbox =
        OrderOutbox.builder()
            .orderId(order.getId())
            .productId(order.getProductId())
            .type(OrderType.ORDER_CREATED)
            .topic(Topic.ORDERS_EVENT)
            .aggregateType(AggregateType.ORDER)
            .aggregateId(aggregateId)
            .traceId(traceId);

    orderOutbox.payload(objectMapper.writeValueAsString(order));

    return orderOutbox.build();
  }

  public static OrderOutbox toDomain(final Order order) {
    return OrderOutbox.builder()
        .orderId(order.getId())
        .productId(order.getProductId())
        .type(OrderType.ORDER_CREATED)
        .build();
  }
}
