package org.inventory.infra.message;

import static org.inventory.share.constants.AppConst.*;

import java.util.UUID;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.inventory.domain.entity.InventoryInbox;
import org.inventory.domain.entity.InventoryOutbox;
import org.inventory.domain.store.InventoryInboxStore;
import org.inventory.domain.store.InventoryOutboxStore;
import org.inventory.infra.message.dto.CreateOrderEvent;
import org.inventory.share.utils.ConvertUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import tools.jackson.databind.ObjectMapper;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class EventHandlerAdapter {

  private final ObjectMapper objectMapper;
  private final InventoryInboxStore inventoryInboxStore;
  private final InventoryOutboxStore inventoryOutboxStore;

  @Bean
  public Consumer<Message<String>> handleReserveStockRequest() {
    return message -> {
      final var eventType = ConvertUtils.toString(message.getHeaders().get(EVENT_TYPE));
      final var aggregateType = ConvertUtils.toString(message.getHeaders().get(AGGREGATE_TYPE));
      final var eventId =
          UUID.fromString(ConvertUtils.toString(message.getHeaders().get(EVENT_ID)));
      final var aggregateId =
          UUID.fromString(ConvertUtils.toString(message.getHeaders().get(AGGREGATE_ID)));
      final var traceId =
          UUID.fromString(ConvertUtils.toString(message.getHeaders().get(TRACE_ID)));

      if (isDuplicatedEvent(eventId)) {
        log.warn("Duplicated event ID: {}", eventId);
        return;
      }

      CreateOrderEvent createOrderEvent =
          objectMapper.readValue(message.getPayload(), CreateOrderEvent.class);

      inventoryInboxStore.save(
          InventoryInbox.builder()
              .eventId(eventId)
              .eventType(eventType)
              .payload(message.getPayload())
              .aggregateId(aggregateId)
              .aggregateType(aggregateType)
              .traceId(traceId)
              .topic("a")
              .build());

      inventoryOutboxStore.save(InventoryOutbox.builder().build());
    };
  }

  private boolean isDuplicatedEvent(final UUID eventId) {
    return inventoryInboxStore.findByEventId(eventId).isPresent();
  }
}
