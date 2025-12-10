package org.inventory.infra.message.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.inventory.share.type.OrderStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderEvent {

  private UUID id;
  private UUID productId;
  private OrderStatus status;
  private int quantity;
}
