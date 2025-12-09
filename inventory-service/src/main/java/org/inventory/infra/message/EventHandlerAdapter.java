package org.inventory.infra.message;

import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.inventory.share.utils.ConvertUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

@Configuration
@Slf4j
public class EventHandlerAdapter {

  @Bean
  public Consumer<Message<String>> handleReserveStockRequest() {
    return message -> {
      log.info("In handleReserveStockRequest {}", message);

      final var eventType = ConvertUtils.toString(message.getHeaders().get("eventType"));

      log.info("Received reserve stock event type {}", eventType);
      log.info("Handle Reserve Stock Request {}", message.getPayload());
    };
  }
}
