package org.payment.domain.store;

import org.payment.domain.entity.PaymentOutbox;

public interface PaymentOutboxStore {

  void save(final PaymentOutbox paymentOutbox);
}
