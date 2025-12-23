package org.booking.infra.adapter;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@FeignClient(value = "inventory-service")
public interface InventoryClient {

    @CircuitBreaker(name="inventoryService")
    @GetMapping("/{id}")
    public String getProductById(final UUID id);
}
