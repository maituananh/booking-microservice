package org.booking.infra.store.impl;

import lombok.RequiredArgsConstructor;
import org.booking.domain.entity.Order;
import org.booking.domain.store.OrderStore;
import org.booking.infra.mapper.OrderInfraMapper;
import org.booking.infra.persistence.OrderRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderStoreImpl implements OrderStore {

  private final OrderRepository orderRepository;

  @Transactional
  @Override
  public Order save(final Order order) {
    final var orderEntity = OrderInfraMapper.toEntity(order);
    final var entitySaved = orderRepository.save(orderEntity);

    return OrderInfraMapper.toDomain(entitySaved);
  }
}
