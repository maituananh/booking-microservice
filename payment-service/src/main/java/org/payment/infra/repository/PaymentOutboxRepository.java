package org.payment.infra.repository;

import java.util.UUID;
import org.payment.infra.entity.PaymentOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentOutboxRepository extends JpaRepository<PaymentOutboxEntity, UUID> {}
