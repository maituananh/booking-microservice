package org.inventory.domain.entity;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import org.common.type.AggregateType;
import org.common.type.EventType;
import org.common.type.OrderStatus;
import org.common.type.Topic;

@Data
@Builder
public class InventoryOutbox {

  private UUID id;
  private UUID aggregateId;
  private AggregateType aggregateType;
  private EventType type;
  private String payload;
  private Topic topic;
  private UUID traceId;
  private UUID eventId;
  private Instant createdAt;
  private Instant updatedAt;

  @Data
  @Builder
  public static class Payload {
    private UUID id;
    private UUID productId;
    private String productName;
    private Long amount;
    private OrderStatus status;
    private int quantity;
  }
}
