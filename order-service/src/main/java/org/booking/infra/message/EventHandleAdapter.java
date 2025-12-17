package org.booking.infra.message;

import static org.common.constants.AppConst.*;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.booking.application.usecase.CreateOrderFailUsecase;
import org.booking.application.usecase.CreateOrderSuccessUsecase;
import org.booking.application.usecase.CreateOrderUsecase;
import org.booking.infra.mapper.OrderInfraMapper;
import org.booking.infra.message.dto.CreateOrderEvent;
import org.booking.infra.message.dto.CreateOrderFailEvent;
import org.booking.infra.message.dto.CreateOrderSuccessEvent;
import org.common.type.EventType;
import org.common.utils.ConvertUtils;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventHandleAdapter {

  private final CreateOrderUsecase createOrderUsecase;
  private final CreateOrderFailUsecase createOrderFailUsecase;
  private final CreateOrderSuccessUsecase createOrderSuccessUsecase;
  private final ObjectMapper objectMapper;

  public void handleEvent(final Message<String> message) {
    final var eventType = ConvertUtils.toString(message.getHeaders().get(EVENT_TYPE));

    log.info("Received event {}", eventType);

    switch (EventType.valueOf(eventType)) {
      case ORDER_CREATED -> handleOrderRequest(message);
      case ORDER_CANCELLED -> handleOrderFailed(message);
      case ORDER_SUCCEEDED -> handleOrderSuccess(message);

      default -> throw new IllegalArgumentException("Unknown event type: " + eventType);
    }
  }

  private void handleOrderRequest(final Message<String> message) {
    final var eventId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(EVENT_ID)));
    final var traceId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(TRACE_ID)));

    final var createOrderEvent =
        objectMapper.readValue(message.getPayload(), CreateOrderEvent.class);

    createOrderUsecase.execute(OrderInfraMapper.toDomain(createOrderEvent, eventId, traceId));
  }

  private void handleOrderFailed(final Message<String> message) {
    final var eventId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(EVENT_ID)));
    final var traceId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(TRACE_ID)));

    final var createOrderFailEvent =
        objectMapper.readValue(message.getPayload(), CreateOrderFailEvent.class);

    createOrderFailUsecase.execute(
        OrderInfraMapper.toDomain(createOrderFailEvent, eventId, traceId));
  }

  private void handleOrderSuccess(final Message<String> message) {
    final var eventId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(EVENT_ID)));
    final var traceId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(TRACE_ID)));

    final var orderSuccessEvent =
        objectMapper.readValue(message.getPayload(), CreateOrderSuccessEvent.class);

    createOrderSuccessUsecase.execute(
        OrderInfraMapper.toDomain(orderSuccessEvent, eventId, traceId));
  }
}
