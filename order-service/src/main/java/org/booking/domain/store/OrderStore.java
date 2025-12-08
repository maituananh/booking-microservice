package org.booking.domain.store;

import org.booking.domain.entity.Order;

public interface OrderStore {

    Order save(Order order);
}
