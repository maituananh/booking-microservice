package org.booking.infra.message.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.common.type.OrderStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderSuccessEvent {

  private UUID id; // orderId
  private UUID productId;
  private OrderStatus status;
  private Integer quantity;
}
