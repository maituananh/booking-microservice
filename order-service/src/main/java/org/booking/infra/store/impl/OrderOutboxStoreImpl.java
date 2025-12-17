package org.booking.infra.store.impl;

import lombok.RequiredArgsConstructor;
import org.booking.domain.entity.OrderOutbox;
import org.booking.domain.store.OrderOutboxStore;
import org.booking.infra.mapper.OrderOutboxInfraMapper;
import org.booking.infra.persistence.OrderOutboxRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderOutboxStoreImpl implements OrderOutboxStore {

  private final OrderOutboxRepository orderOutboxRepository;

  @Transactional
  public OrderOutbox save(final OrderOutbox orderOutbox) {
    final var orderOutboxEntity = OrderOutboxInfraMapper.toEntity(orderOutbox);
    final var entitySave = orderOutboxRepository.save(orderOutboxEntity);

    return OrderOutboxInfraMapper.toDomain(entitySave);
  }
}
