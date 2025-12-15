package org.payment.infra.store.impl;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.payment.domain.entity.PaymentInbox;
import org.payment.domain.store.PaymentInboxStore;
import org.payment.infra.repository.PaymentInboxRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentInboxStoreImpl implements PaymentInboxStore {

  private final PaymentInboxRepository paymentInboxRepository;

  @Override
  public void save(PaymentInbox paymentInbox) {}

  @Override
  public Optional<PaymentInbox> findByEventId(UUID id) {
    return Optional.empty();
  }
}
