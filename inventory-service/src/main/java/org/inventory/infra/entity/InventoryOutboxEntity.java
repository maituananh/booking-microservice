package org.inventory.infra.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.Data;

@Table(name = "inventories_outbox")
@Entity
@Data
public class InventoryOutboxEntity {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "aggregate_id")
  private UUID aggregateId;

  @Column(name = "aggregate_type")
  private String aggregateType;

  @Column(name = "type")
  private String type;

  @Column(name = "payload", columnDefinition = "jsonb")
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
