package org.inventory.infra.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

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
    private UUID aggregateType;

    @Column(name = "type")
    private UUID type;

    @Column(name = "payload")
    private String payload;

    @Column(name = "topic")
    private UUID topic;

    @Column(name = "trace_id")
    private UUID traceId;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
