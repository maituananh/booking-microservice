package org.payment.infra.payment;

import org.payment.infra.payment.request.PaymentRequest;
import org.payment.infra.payment.response.PaymentResponse;

public interface PaymentGateway {

  PaymentResponse handlePayment(final PaymentRequest request);
}
