package org.payment.domain.store;

import java.util.Optional;
import java.util.UUID;
import org.payment.domain.entity.PaymentInbox;

public interface PaymentInboxStore {

  void save(final PaymentInbox paymentInbox);

  Optional<PaymentInbox> findByEventId(final UUID id);
}
