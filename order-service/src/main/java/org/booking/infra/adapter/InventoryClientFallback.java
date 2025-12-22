package org.booking.infra.adapter;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InventoryClientFallback implements InventoryClient {
    @Override
    public String getProductById(UUID id) {
        return "";
    }
}
