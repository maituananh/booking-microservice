package org.inventory.infra.store.impl;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.inventory.domain.entity.Product;
import org.inventory.domain.store.ProductStore;
import org.inventory.infra.mapper.ProductMapper;
import org.inventory.infra.repository.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductStoreImpl implements ProductStore {

  private final ProductRepository productRepository;

  @Override
  public Optional<Product> findById(final UUID id) {
    return productRepository.findById(id).map(ProductMapper::toDomain).or(Optional::empty);
  }

  @Transactional
  @Override
  public void save(final Product product) {
    final var entity = productRepository.findById(product.getId()).orElseThrow();

    productRepository.save(ProductMapper.updateQuantity(product, entity));
  }
}
