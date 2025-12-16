package org.payment.infra.message;

import static org.common.constants.AppConst.*;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.common.type.AggregateType;
import org.common.type.Topic;
import org.common.utils.ConvertUtils;
import org.payment.domain.entity.PaymentInbox;
import org.payment.domain.entity.PaymentOutbox;
import org.payment.domain.store.PaymentInboxStore;
import org.payment.domain.store.PaymentOutboxStore;
import org.payment.infra.message.dto.PaymentEvent;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class EventHandlerAdapter {

  private final ObjectMapper objectMapper;

  private final PaymentOutboxStore paymentOutboxStore;
  private final PaymentInboxStore paymentInboxStore;

  @Transactional
  public void handlePayment(final Message<String> message) {
    final var eventType = ConvertUtils.toString(message.getHeaders().get(EVENT_TYPE));
    final var eventId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(EVENT_ID)));
    final var traceId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(TRACE_ID)));

    log.info(
        "EventHandlerAdapter at Payment: event_type {}, event_id {}, trace_id {}",
        eventType,
        eventId,
        traceId);

    if (isDuplicatedEvent(eventId)) {
      log.warn("Duplicated event ID: {}", eventId);
      return;
    }

    PaymentEvent paymentEvent = objectMapper.readValue(message.getPayload(), PaymentEvent.class);

    final var aggregateId = UUID.randomUUID();

    paymentInboxStore.save(
        PaymentInbox.builder()
            .eventId(eventId)
            .aggregateId(aggregateId)
            .aggregateType(AggregateType.INVENTORY)
            .traceId(traceId)
            .build());

    paymentOutboxStore.save(
        PaymentOutbox.builder()
            .aggregateId(aggregateId)
            .aggregateType(AggregateType.INVENTORY)
            .topic(Topic.PAYMENTS_EVENT)
            .traceId(traceId)
            .eventId(eventId)
            .payload(message.getPayload())
            .type(eventType)
            .build());
  }

  private boolean isDuplicatedEvent(final UUID eventId) {
    return paymentInboxStore.findByEventId(eventId).isPresent();
  }
}
