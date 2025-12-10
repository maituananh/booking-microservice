package org.inventory.domain.store;

import java.util.Optional;
import java.util.UUID;
import org.inventory.domain.entity.InventoryInbox;

public interface InventoryInboxStore {

  void save(final InventoryInbox inventoryInbox);

  Optional<InventoryInbox> findByEventId(final UUID id);
}
