package org.booking.application.service;

import lombok.RequiredArgsConstructor;
import org.booking.application.mapper.OrderAppMapper;
import org.booking.application.mapper.OrderOutboxAppMapper;
import org.booking.domain.entity.Order;
import org.booking.domain.store.OrderOutboxStore;
import org.booking.domain.store.OrderStore;
import org.booking.presentation.request.CreateOrderRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

  private final ObjectMapper objectMapper;
  private final OrderOutboxStore orderOutboxStore;
  private final OrderStore orderStore;

  @Transactional
  public Order createOrder(final CreateOrderRequest createOrderRequest) {
    final var orderDomain = OrderAppMapper.toDomain(createOrderRequest);
    final var orderSaved = orderStore.save(orderDomain);

    final var orderOutboxDomain = OrderOutboxAppMapper.toCreateOrder(orderSaved, objectMapper);
    orderOutboxStore.save(orderOutboxDomain);

    return orderSaved;
  }
}
