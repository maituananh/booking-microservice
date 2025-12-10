package org.inventory.infra.repository;

import java.util.Optional;
import java.util.UUID;
import org.inventory.infra.entity.InventoryInboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryInboxRepository extends JpaRepository<InventoryInboxEntity, UUID> {

  Optional<InventoryInboxEntity> findByEventId(final UUID eventId);
}
