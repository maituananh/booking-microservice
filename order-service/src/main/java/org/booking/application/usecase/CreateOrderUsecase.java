package org.booking.application.usecase;

import lombok.RequiredArgsConstructor;
import org.booking.domain.entity.Order;
import org.booking.domain.entity.OrderOutbox;
import org.booking.domain.store.OrderOutboxStore;
import org.booking.domain.store.OrderStore;
import org.common.type.AggregateType;
import org.common.type.OrderStatus;
import org.common.type.OrderType;
import org.common.type.Topic;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateOrderUsecase {

  private final OrderStore orderStore;
  private final OrderOutboxStore orderOutboxStore;
  private final ObjectMapper objectMapper;

  @Transactional
  public void execute(final Order order) {
    final var orderSaved =
        orderStore.save(
            Order.builder()
                .traceId(order.getTraceId())
                .eventId(order.getEventId())
                .status(OrderStatus.PENDING)
                .quantity(order.getQuantity())
                .productId(order.getProductId())
                .build());

    final var payload =
        OrderOutbox.Payload.builder()
            .id(orderSaved.getId())
            .productId(orderSaved.getProductId())
            .quantity(order.getQuantity())
            .status(orderSaved.getStatus())
            .build();

    orderOutboxStore.save(
        OrderOutbox.builder()
            .orderId(order.getId())
            .productId(order.getProductId())
            .type(OrderType.ORDER_CREATED)
            .topic(Topic.ORDERS_EVENT)
            .aggregateType(AggregateType.ORDER)
            .aggregateId(orderSaved.getId())
            .traceId(order.getTraceId())
            .eventId(order.getEventId())
            .payload(objectMapper.writeValueAsString(payload))
            .build());
  }
}
