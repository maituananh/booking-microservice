package org.booking.domain.entity;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booking.share.type.OrderStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  private UUID id;
  private UUID productId;
  private OrderStatus status;
}
