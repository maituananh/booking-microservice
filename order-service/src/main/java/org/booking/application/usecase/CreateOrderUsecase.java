package org.booking.application.usecase;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.booking.domain.entity.Order;
import org.booking.domain.entity.OrderOutbox;
import org.booking.domain.store.OrderOutboxStore;
import org.booking.domain.store.OrderStore;
import org.booking.presentation.request.CreateOrderRequest;
import org.common.type.AggregateType;
import org.common.type.OrderStatus;
import org.common.type.OrderType;
import org.common.type.Topic;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateOrderUsecase {

  private final OrderStore orderStore;
  private final OrderOutboxStore orderOutboxStore;

  public void execute(final CreateOrderRequest createOrderRequest) {
    final var order =
        Order.builder()
            .traceId(UUID.randomUUID())
            .eventId(UUID.randomUUID())
            .productId(createOrderRequest.getProductId())
            .quantity(createOrderRequest.getQuantity())
            .build();

    execute(order);
  }

  @Transactional
  public void execute(final Order order) {
    final var orderSaved =
        orderStore.save(
            Order.builder()
                .traceId(order.getTraceId())
                .eventId(order.getEventId())
                .status(OrderStatus.PENDING)
                .productId(order.getProductId())
                .build());

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
            .build());
  }
}
