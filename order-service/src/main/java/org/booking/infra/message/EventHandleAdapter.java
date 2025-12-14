package org.booking.infra.message;

import static org.constants.AppConst.*;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.booking.domain.store.OrderStore;
import org.booking.infra.message.dto.CreateOrderEvent;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.type.OrderStatus;
import org.utils.ConvertUtils;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class EventHandleAdapter {

  private final OrderStore orderStore;
  private final ObjectMapper objectMapper;

  @Transactional
  public void consumerOrderSuccess(final Message<String> message) {
    final var eventType = ConvertUtils.toString(message.getHeaders().get(EVENT_TYPE));
    final var eventId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(EVENT_ID)));
    final var traceId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(TRACE_ID)));

    log.info(
        "EventHandlerAdapter at Order: event_type {}, event_id {}, trace_id {}",
        eventType,
        eventId,
        traceId);

    CreateOrderEvent createOrderEvent =
        objectMapper.readValue(message.getPayload(), CreateOrderEvent.class);

    final var orderOptional = orderStore.findById(createOrderEvent.getId());

    if (orderOptional.isPresent()) {
      final var order = orderOptional.get();

      order.setStatus(OrderStatus.COMPLETED);
      orderStore.save(order);
    }
  }
}
