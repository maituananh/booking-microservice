package org.booking.infra.persistence;

import org.booking.infra.entity.OrderOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderOutboxRepository extends JpaRepository<OrderOutboxEntity, UUID> {
}
