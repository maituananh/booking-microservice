package org.inventory.infra.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Table(name = "inventories_inbox")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryInboxEntity {

  @Id
  @Column(name = "event_id")
  private UUID eventId;

  @Column(name = "aggregate_id")
  private UUID aggregateId;

  @Column(name = "aggregate_type")
  private String aggregateType;

  @Column(name = "event_type")
  private String eventType;

  @Column(name = "payload", columnDefinition = "jsonb")
  @JdbcTypeCode(SqlTypes.JSON)
  private String payload;

  @Column(name = "topic")
  private String topic;

  @Column(name = "trace_id")
  private UUID traceId;

  @Column(name = "created_at")
  private Instant createdAt;

  @Column(name = "updated_at")
  private Instant updatedAt;
}
