package org.booking.domain.entity;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booking.share.type.AggregateType;
import org.booking.share.type.OrderType;
import org.booking.share.type.Topic;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderOutbox {

  private UUID productId;
  private UUID orderId;
  private Topic topic;
  private UUID aggregateId;
  private AggregateType aggregateType;
  private OrderType type;
  private String payload;
  private UUID traceId;
}
