package org.booking.infra.persistence;

import java.util.UUID;
import org.booking.infra.entity.OrderOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderOutboxRepository extends JpaRepository<OrderOutboxEntity, UUID> {}
