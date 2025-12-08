package org.booking.infra.kafka;

import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class OrderProducer {

    public Supplier<String> orderProducer() {
        return () -> "orderProducer";
    }
}
