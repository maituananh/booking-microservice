package org.payment.infra.store.impl;

import lombok.RequiredArgsConstructor;
import org.payment.domain.entity.PaymentOutbox;
import org.payment.domain.store.PaymentOutboxStore;
import org.payment.infra.mapper.PaymentOutboxMapper;
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
  public void save(final PaymentOutbox paymentOutbox) {
    final var entity = PaymentOutboxMapper.toEntity(paymentOutbox);

    paymentOutboxRepository.save(entity);
  }
}
