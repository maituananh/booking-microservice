package org.payment.infra.payment;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.payment.infra.payment.request.PaymentRequest;
import org.payment.infra.payment.response.PaymentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentAdapter {

  @Value("${stripe.secret-ket}")
  private String secretKey;

  public PaymentAdapter() {
    Stripe.apiKey = secretKey;
  }

  public PaymentResponse payment(final PaymentRequest request) {
    final var productData =
        SessionCreateParams.LineItem.PriceData.ProductData.builder()
            .setName(request.getProductName())
            .build();

    final var priceData =
        SessionCreateParams.LineItem.PriceData.builder()
            .setCurrency(request.getCurrency())
            .setUnitAmount(request.getAmount())
            .setProductData(productData)
            .build();

    final var lineItem =
        SessionCreateParams.LineItem.builder()
            .setQuantity(request.getQuantity())
            .setPriceData(priceData)
            .build();

    final var sessionCreateParams =
        SessionCreateParams.builder()
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl("")
            .setCancelUrl("")
            .addLineItem(lineItem)
            .build();

    Session session = null;
    try {
      session = Session.create(sessionCreateParams);

      return PaymentResponse.builder()
          .status(session.getStatus())
          .sessionId(session.getId())
          .sessionUrl(session.getUrl())
          .build();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
