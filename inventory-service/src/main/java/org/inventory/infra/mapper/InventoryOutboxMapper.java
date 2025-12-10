package org.inventory.infra.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.inventory.domain.entity.InventoryOutbox;
import org.inventory.infra.entity.InventoryOutboxEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InventoryOutboxMapper {

  public static InventoryOutboxEntity toEntity(final InventoryOutbox inventoryOutbox) {
    return InventoryOutboxEntity.builder()
        .id(inventoryOutbox.getId())
        .type(inventoryOutbox.getType())
        .topic(inventoryOutbox.getTopic())
        .aggregateId(inventoryOutbox.getAggregateId())
        .aggregateType(inventoryOutbox.getAggregateType())
        .payload(inventoryOutbox.getPayload())
        .traceId(inventoryOutbox.getTraceId())
        .build();
  }

  public static InventoryOutbox toDomain(final InventoryOutboxEntity entity) {
    return InventoryOutbox.builder()
        .id(entity.getId())
        .type(entity.getType())
        .topic(entity.getTopic())
        .aggregateId(entity.getAggregateId())
        .aggregateType(entity.getAggregateType())
        .payload(entity.getPayload())
        .traceId(entity.getTraceId())
        .build();
  }
}
