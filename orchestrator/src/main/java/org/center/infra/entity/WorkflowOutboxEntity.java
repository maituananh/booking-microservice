package org.center.infra.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.type.AggregateType;
import org.type.Topic;

@Table(name = "workflows_outbox")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowOutboxEntity {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "aggregate_id")
  private UUID aggregateId;

  @Column(name = "event_id")
  private UUID eventId;

  @Column(name = "aggregate_type")
  @Enumerated(EnumType.STRING)
  private AggregateType aggregateType;

  @Column(name = "type")
  private String type;

  @Column(name = "payload", columnDefinition = "jsonb")
  @JdbcTypeCode(SqlTypes.JSON)
  private String payload;

  @Column(name = "topic")
  @Enumerated(EnumType.STRING)
  private Topic topic;

  @Column(name = "trace_id")
  private UUID traceId;

  @Column(name = "created_at")
  private Instant createdAt;

  @Column(name = "updated_at")
  private Instant updatedAt;
}
