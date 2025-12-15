package org.payment.infra.repository;

import java.util.UUID;
import org.payment.infra.entity.PaymentInboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentInboxRepository extends JpaRepository<PaymentInboxEntity, UUID> {}
