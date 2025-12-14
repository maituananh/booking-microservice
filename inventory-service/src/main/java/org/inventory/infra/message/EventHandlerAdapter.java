package org.inventory.infra.message;

import static org.constants.AppConst.*;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.inventory.domain.entity.InventoryInbox;
import org.inventory.domain.entity.InventoryOutbox;
import org.inventory.domain.store.InventoryInboxStore;
import org.inventory.domain.store.InventoryOutboxStore;
import org.inventory.infra.message.dto.CreateOrderEvent;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.type.AggregateType;
import org.type.Topic;
import org.utils.ConvertUtils;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class EventHandlerAdapter {

  private final ObjectMapper objectMapper;
  private final InventoryInboxStore inventoryInboxStore;
  private final InventoryOutboxStore inventoryOutboxStore;

  @Transactional
  public void handleReserveStockRequest(final Message<String> message) {
    final var eventType = ConvertUtils.toString(message.getHeaders().get(EVENT_TYPE));
    final var eventId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(EVENT_ID)));
    final var traceId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(TRACE_ID)));

    log.info(
        "EventHandlerAdapter at Inventory: event_type {}, event_id {}, trace_id {}",
        eventType,
        eventId,
        traceId);

    if (isDuplicatedEvent(eventId)) {
      log.warn("Duplicated event ID: {}", eventId);
      return;
    }

    CreateOrderEvent createOrderEvent =
        objectMapper.readValue(message.getPayload(), CreateOrderEvent.class);

    final var aggregateId = UUID.randomUUID();

    inventoryInboxStore.save(
        InventoryInbox.builder()
            .eventId(eventId)
            .aggregateId(aggregateId)
            .aggregateType(AggregateType.INVENTORY)
            .traceId(traceId)
            .build());

    inventoryOutboxStore.save(
        InventoryOutbox.builder()
            .aggregateId(aggregateId)
            .aggregateType(AggregateType.INVENTORY)
            .topic(Topic.ORDER_PAYMENT)
            .traceId(traceId)
            .payload(message.getPayload())
            .type(eventType)
            .build());
  }

  private boolean isDuplicatedEvent(final UUID eventId) {
    return inventoryInboxStore.findByEventId(eventId).isPresent();
  }
}
