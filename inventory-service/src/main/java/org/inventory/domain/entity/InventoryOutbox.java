package org.inventory.domain.entity;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import org.common.type.AggregateType;
import org.common.type.Topic;

@Data
@Builder
public class InventoryOutbox {

  private UUID id;
  private UUID aggregateId;
  private AggregateType aggregateType;
  private String type;
  private String payload;
  private Topic topic;
  private UUID traceId;
  private UUID eventId;
  private Instant createdAt;
  private Instant updatedAt;
}
