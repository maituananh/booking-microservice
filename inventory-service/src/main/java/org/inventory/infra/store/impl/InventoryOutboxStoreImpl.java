package org.inventory.infra.store.impl;

import lombok.RequiredArgsConstructor;
import org.inventory.domain.entity.InventoryOutbox;
import org.inventory.domain.store.InventoryOutboxStore;
import org.inventory.infra.mapper.InventoryOutboxMapper;
import org.inventory.infra.repository.InventoryOutboxRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InventoryOutboxStoreImpl implements InventoryOutboxStore {

  private final InventoryOutboxRepository inventoryOutboxRepository;

  @Transactional
  @Override
  public void save(final InventoryOutbox inventoryOutbox) {
    final var inventoryOutboxEntity = InventoryOutboxMapper.toEntity(inventoryOutbox);

    inventoryOutboxRepository.save(inventoryOutboxEntity);
  }
}
