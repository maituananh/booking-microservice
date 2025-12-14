package org.domain.entity;

import java.time.Instant;
import java.util.UUID;
import lombok.*;
import org.type.AggregateType;
import org.type.Topic;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowOutbox {

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
