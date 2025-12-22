package org.booking.application.usecase;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.booking.infra.adapter.InventoryAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetInventoryUsecase {

  private final InventoryAdapter inventoryAdapter;

  public String execute() {
    return inventoryAdapter.getInventoryById(UUID.randomUUID());
  }
}
