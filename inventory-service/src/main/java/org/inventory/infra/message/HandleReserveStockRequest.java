package org.inventory.infra.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class HandleReserveStockRequest {

    public Consumer<Message<String>> handleReserveStockRequest() {
        return message -> {
            log.info("Handle Reserve Stock Request {}", message.getPayload());
        };
    }
}
