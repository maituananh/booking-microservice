package org.booking.domain.store;

import org.booking.domain.entity.OrderOutbox;

public interface OrderOutboxStore {

  OrderOutbox save(OrderOutbox orderOutbox);
}
