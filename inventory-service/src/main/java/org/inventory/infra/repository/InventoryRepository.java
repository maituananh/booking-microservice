package org.inventory.infra.repository;

import java.util.UUID;
import org.inventory.infra.entity.InventoryOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryOutboxEntity, UUID> {}
