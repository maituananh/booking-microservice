package org.booking.infra.adapter;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryAdapter {

  private final InventoryClient inventoryClient;

  public String getInventoryById(final UUID id) {
    return inventoryClient.getProductById(id);
  }
}
