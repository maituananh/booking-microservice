package org.booking.infra.store.impl;

import java.util.Optional;
import java.util.UUID;
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

  @Override
  public Optional<Order> findById(final UUID id) {
    return orderRepository.findById(id).map(OrderInfraMapper::toDomain).or(Optional::empty);
  }
}
