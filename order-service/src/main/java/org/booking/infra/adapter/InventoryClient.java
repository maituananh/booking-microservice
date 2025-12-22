package org.booking.infra.adapter;

import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    value = "inventoryService",
    name = "inventoryService",
    url = "http://localhost:8081",
    fallback = InventoryClientFallback.class)
public interface InventoryClient {

  @GetMapping("/api/inventories/{id}")
  String getProductById(@PathVariable final UUID id);
}
