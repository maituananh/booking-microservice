package org.booking.infra.adapter;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "inventoryService", name = "inventoryService", url = "http://localhost:8081")
public interface InventoryClient {

  @Retry(name = "inventoryService", fallbackMethod = "fallbackGetProduct")
  @CircuitBreaker(name = "inventoryService")
  @GetMapping("/api/inventories/{id}")
  String getProductById(@PathVariable final UUID id);

  // Must use CallNotPermittedException, if not it always return fallbackMethod
  default String fallbackGetProduct(UUID id, CallNotPermittedException t) {
    return "OUT_OF_STOCK";
  }
}
