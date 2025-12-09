package org.inventory.infra.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class ReserveStockRequestHandlerConfig {

    @Bean
    public Consumer<Message<String>> handleReserveStockRequest() {
        return message -> {
            log.info("Handle Reserve Stock Request {}", message.getPayload());
        };
    }
}
