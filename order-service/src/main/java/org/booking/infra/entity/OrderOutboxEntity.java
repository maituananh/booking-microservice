package org.booking.infra.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.type.AggregateType;
import org.type.OrderType;
import org.type.Topic;

@Table(name = "orders_outbox")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderOutboxEntity {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "aggregate_id")
  private UUID aggregateId;

  @Column(name = "aggregate_type")
  @Enumerated(EnumType.STRING)
  private AggregateType aggregateType; // Order, Customer, Payment

  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private OrderType type; // OrderCreated, OrderCancelled

  @Column(name = "payload", columnDefinition = "jsonb")
  @JdbcTypeCode(SqlTypes.JSON)
  private String payload;

  @Column(name = "topic")
  @Enumerated(EnumType.STRING)
  private Topic topic; // orders.event

  @Column(name = "trace_id")
  private UUID traceId;

  @Column(name = "event_id")
  private UUID eventId;

  @Column(name = "created_at")
  private Instant createdAt;

  @Column(name = "updated_at")
  private Instant updatedAt;
}
