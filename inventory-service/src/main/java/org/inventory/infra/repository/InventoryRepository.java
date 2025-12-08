package org.inventory.infra.repository;

import org.inventory.infra.entity.InventoryOutboxEntity;
import org.inventory.infra.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryOutboxEntity, UUID> {
}
