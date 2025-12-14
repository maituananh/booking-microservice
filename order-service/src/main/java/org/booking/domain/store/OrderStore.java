package org.booking.domain.store;

import java.util.Optional;
import java.util.UUID;
import org.booking.domain.entity.Order;

public interface OrderStore {

  Order save(final Order order);

  Optional<Order> findById(final UUID id);
}
