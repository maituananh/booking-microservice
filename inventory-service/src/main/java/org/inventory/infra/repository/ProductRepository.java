package org.inventory.infra.repository;

import java.util.UUID;
import org.inventory.infra.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {}
