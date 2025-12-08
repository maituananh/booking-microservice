package org.inventory.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private UUID id;
    private Integer availableQuantity;
    private Integer reservedQuantity;
    private Integer onHandQuantity;
    private Instant createdAt;
    private Instant updatedAt;
}
