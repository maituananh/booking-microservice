package org.domain.entity;

import java.util.UUID;
import lombok.*;
import org.share.type.AggregateType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowOutbox {

  private UUID productId;
  private UUID orderId;
  private Topic topic;
  private UUID aggregateId;
  private AggregateType aggregateType;
  private OrderType type;
  private String payload;
  private UUID traceId;
}
