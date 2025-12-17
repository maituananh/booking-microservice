package org.inventory.domain.entity;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

  private UUID id;
  private String name;
  private Integer availableQuantity;
  private Integer reservedQuantity;
  private Integer onHandQuantity;
  private Long amount;
  private Instant createdAt;
  private Instant updatedAt;
}
