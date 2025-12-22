package org.booking.infra.adapter;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class InventoryClientFallback implements InventoryClient {

  @Override
  public String getProductById(UUID id) {
    return "circuitbreaker worked";
  }
}
