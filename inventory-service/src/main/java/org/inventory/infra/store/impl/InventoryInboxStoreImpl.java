package org.inventory.infra.store.impl;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.inventory.domain.entity.InventoryInbox;
import org.inventory.domain.store.InventoryInboxStore;
import org.inventory.infra.mapper.InventoryInboxMapper;
import org.inventory.infra.repository.InventoryInboxRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InventoryInboxStoreImpl implements InventoryInboxStore {

  private final InventoryInboxRepository inventoryInboxRepository;

  @Transactional
  @Override
  public void save(final InventoryInbox inventoryInbox) {
    final var inboxEntity = InventoryInboxMapper.toEntity(inventoryInbox);

    inventoryInboxRepository.save(inboxEntity);
  }

  @Override
  public Optional<InventoryInbox> findByEventId(final UUID id) {
    return inventoryInboxRepository
        .findByEventId(id)
        .map(InventoryInboxMapper::toDomain)
        .or(Optional::empty);
  }
}
