package org.booking.domain.entity;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.type.OrderStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  private UUID id;
  private UUID productId;
  private OrderStatus status;
  private int quantity;
  private Instant createdAt;
  private Instant updatedAt;
}
