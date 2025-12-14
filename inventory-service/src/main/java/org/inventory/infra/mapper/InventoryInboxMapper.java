package org.inventory.infra.mapper;

import java.time.Instant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.inventory.domain.entity.InventoryInbox;
import org.inventory.infra.entity.InventoryInboxEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InventoryInboxMapper {

  public static InventoryInboxEntity toEntity(final InventoryInbox inventoryInbox) {
    return InventoryInboxEntity.builder()
        .eventId(inventoryInbox.getEventId())
        .aggregateId(inventoryInbox.getAggregateId())
        .aggregateType(inventoryInbox.getAggregateType())
        .traceId(inventoryInbox.getTraceId())
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  public static InventoryInbox toDomain(final InventoryInboxEntity entity) {
    return InventoryInbox.builder()
        .eventId(entity.getEventId())
        .aggregateId(entity.getAggregateId())
        .aggregateType(entity.getAggregateType())
        .traceId(entity.getTraceId())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .build();
  }
}
