package org.payment.infra.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.common.type.AggregateType;

@Table(name = "payments_inbox")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInboxEntity {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "event_id")
  private UUID eventId;

  @Column(name = "aggregate_id")
  private UUID aggregateId;

  @Column(name = "aggregate_type")
  @Enumerated(EnumType.STRING)
  private AggregateType aggregateType;

  @Column(name = "trace_id")
  private UUID traceId;

  @Column(name = "created_at")
  private Instant createdAt;

  @Column(name = "updated_at")
  private Instant updatedAt;
}
