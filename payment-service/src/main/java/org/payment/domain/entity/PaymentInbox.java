package org.payment.domain.entity;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.common.type.AggregateType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInbox {

  private UUID id;
  private UUID eventId;
  private UUID aggregateId;
  private AggregateType aggregateType;
  private UUID traceId;
  private Instant createdAt;
  private Instant updatedAt;
}
