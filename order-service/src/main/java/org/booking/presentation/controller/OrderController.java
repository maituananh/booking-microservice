package org.booking.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.booking.application.usecase.CreateOrderUsecase;
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
    createOrderUsecase.execute(createOrderRequest);
    return null;
  }
}
