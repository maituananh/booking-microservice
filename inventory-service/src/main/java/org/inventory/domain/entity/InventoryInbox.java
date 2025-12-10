package org.inventory.domain.entity;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryInbox {

  private UUID eventId;
  private UUID aggregateId;
  private String aggregateType;
  private String eventType;
  private String payload;
  private String topic;
  private UUID traceId;
  private Instant createdAt;
  private Instant updatedAt;
}
