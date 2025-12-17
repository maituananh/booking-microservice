package org.inventory.domain.store;

import java.util.Optional;
import java.util.UUID;
import org.inventory.domain.entity.Product;

public interface ProductStore {

  Optional<Product> findById(final UUID id);

  void save(final Product product);
}
