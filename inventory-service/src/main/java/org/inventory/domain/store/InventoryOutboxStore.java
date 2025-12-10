package org.inventory.domain.store;

import org.inventory.domain.entity.InventoryOutbox;

public interface InventoryOutboxStore {

  void save(final InventoryOutbox inventoryOutbox);
}
