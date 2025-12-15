package org.payment.infra.store.impl;

import lombok.RequiredArgsConstructor;
import org.payment.domain.entity.PaymentOutbox;
import org.payment.domain.store.PaymentOutboxStore;
import org.payment.infra.repository.PaymentOutboxRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentOutboxStoreImpl implements PaymentOutboxStore {

  private final PaymentOutboxRepository paymentOutboxRepository;

  @Transactional
  @Override
  public void save(PaymentOutbox paymentOutbox) {}
}
