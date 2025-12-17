package org.inventory.infra.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.inventory.domain.entity.Product;
import org.inventory.infra.entity.ProductEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapper {

  public static Product toDomain(final ProductEntity productEntity) {
    return Product.builder()
        .id(productEntity.getId())
        .amount(productEntity.getAmount())
        .availableQuantity(productEntity.getAvailableQuantity())
        .onHandQuantity(productEntity.getOnHandQuantity())
        .reservedQuantity(productEntity.getReservedQuantity())
        .createdAt(productEntity.getCreatedAt())
        .updatedAt(productEntity.getUpdatedAt())
        .build();
  }

  public static ProductEntity updateQuantity(
      final Product product, final ProductEntity productEntity) {
    productEntity.setAvailableQuantity(product.getAvailableQuantity());
    productEntity.setReservedQuantity(product.getReservedQuantity());

    return productEntity;
  }
}
