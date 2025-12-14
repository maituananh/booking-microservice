package org.inventory.infra.config;

import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.inventory.infra.message.EventHandlerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

@Configuration
@RequiredArgsConstructor
public class StreamBindingConfig {

  private final EventHandlerAdapter eventHandleAdapter;

  @Bean
  public Consumer<Message<String>> handleReserveStockRequest() {
    return eventHandleAdapter::handleReserveStockRequest;
  }
}
