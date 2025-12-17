package org.payment.infra.payment.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
  private String productName;
  private Long amount;
  private String currency;
  private Long quantity;

  public String getCurrency() {
    return currency != null ? currency : "USD";
  }
}
