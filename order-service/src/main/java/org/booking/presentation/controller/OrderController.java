package org.booking.presentation.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.booking.application.usecase.CreateOrderUsecase;
import org.booking.domain.entity.Order;
import org.booking.presentation.request.CreateOrderRequest;
import org.booking.presentation.response.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

  private final CreateOrderUsecase createOrderUsecase;

  @PostMapping
  public ResponseEntity<OrderResponse> addOrder(
      @RequestBody CreateOrderRequest createOrderRequest) {
    final var order =
        Order.builder()
            .traceId(UUID.randomUUID())
            .eventId(UUID.randomUUID())
            .productId(createOrderRequest.getProductId())
            .quantity(createOrderRequest.getQuantity())
            .build();

    createOrderUsecase.execute(order);
    return null;
  }
}
