package org.booking.application.usecase;

import java.util.UUID;
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

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateOrderSuccessUsecase {

  private final OrderStore orderStore;
  private final OrderOutboxStore orderOutboxStore;

  @Transactional
  public void execute(final Order order) {
    final var orderFound = orderStore.findById(order.getId()).orElseThrow();
    orderFound.setStatus(OrderStatus.COMPLETED);

    orderStore.save(orderFound);

    orderOutboxStore.save(
        OrderOutbox.builder()
            .orderId(order.getId())
            .productId(order.getProductId())
            .type(OrderType.ORDER_CREATED)
            .topic(Topic.ORDERS_COMMAND)
            .aggregateType(AggregateType.ORDER)
            .aggregateId(orderFound.getId())
            .traceId(order.getTraceId())
            .eventId(UUID.randomUUID())
            .build());
  }
}
