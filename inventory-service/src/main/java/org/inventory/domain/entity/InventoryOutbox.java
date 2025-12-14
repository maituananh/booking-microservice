package org.inventory.domain.entity;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import org.type.AggregateType;
import org.type.Topic;

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
  private Instant createdAt;
  private Instant updatedAt;
}
