package org.inventory.infra.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.common.type.AggregateType;
import org.common.type.Topic;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Table(name = "inventories_outbox")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryOutboxEntity {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "aggregate_id")
  private UUID aggregateId;

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

  @Column(name = "event_id")
  private UUID eventId;

  @Column(name = "created_at")
  private Instant createdAt;

  @Column(name = "updated_at")
  private Instant updatedAt;
}
