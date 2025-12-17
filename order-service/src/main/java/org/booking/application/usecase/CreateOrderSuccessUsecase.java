package org.booking.application.usecase;

import lombok.RequiredArgsConstructor;
import org.booking.domain.entity.Order;
import org.booking.domain.store.OrderStore;
import org.common.type.OrderStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateOrderSuccessUsecase {

  private final OrderStore orderStore;

  @Transactional
  public void execute(final Order order) {
    final var orderFound = orderStore.findById(order.getId()).orElseThrow();
    orderFound.setStatus(OrderStatus.COMPLETED);

    orderStore.save(orderFound);
  }
}
