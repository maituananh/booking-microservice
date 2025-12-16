package org.inventory.infra.mapper;

import java.time.Instant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.common.type.Topic;
import org.inventory.domain.entity.InventoryOutbox;
import org.inventory.infra.entity.InventoryOutboxEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InventoryOutboxMapper {

  public static InventoryOutboxEntity toEntity(final InventoryOutbox inventoryOutbox) {
    return InventoryOutboxEntity.builder()
        .id(inventoryOutbox.getId())
        .type(inventoryOutbox.getType())
        .topic(inventoryOutbox.getTopic().getValue())
        .aggregateId(inventoryOutbox.getAggregateId())
        .aggregateType(inventoryOutbox.getAggregateType())
        .payload(inventoryOutbox.getPayload())
        .traceId(inventoryOutbox.getTraceId())
        .eventId(inventoryOutbox.getEventId())
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  public static InventoryOutbox toDomain(final InventoryOutboxEntity entity) {
    return InventoryOutbox.builder()
        .id(entity.getId())
        .type(entity.getType())
        .topic(Topic.valueOf(entity.getTopic()))
        .aggregateId(entity.getAggregateId())
        .aggregateType(entity.getAggregateType())
        .payload(entity.getPayload())
        .traceId(entity.getTraceId())
        .eventId(entity.getEventId())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .build();
  }
}
