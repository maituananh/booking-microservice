package org.booking.application.service;

import lombok.RequiredArgsConstructor;
import org.booking.infra.kafka.OrderProducer;
import org.booking.presentation.request.CreateOrderRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderProducer orderProducer;

    public void createOrder(CreateOrderRequest createOrderRequest) {
        orderProducer.orderProducer();
    }
}
