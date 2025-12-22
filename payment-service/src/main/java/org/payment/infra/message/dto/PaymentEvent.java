package org.payment.infra.message.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.common.type.OrderStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEvent {

  private UUID id;
  private UUID productId;
  private String productName;
  private Long amount;
  private OrderStatus status;
  private Long quantity;
}
