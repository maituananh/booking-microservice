package org.inventory.domain.entity;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryOutbox {

  private UUID id;
  private UUID aggregateId;
  private UUID aggregateType;
  private UUID type;
  private String payload;
  private UUID topic;
  private UUID traceId;
  private Instant createdAt;
  private Instant updatedAt;
}
