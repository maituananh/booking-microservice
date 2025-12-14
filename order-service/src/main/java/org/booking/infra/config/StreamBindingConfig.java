package org.booking.infra.config;

import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.booking.infra.message.EventHandleAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

@Configuration
@RequiredArgsConstructor
public class StreamBindingConfig {

  private final EventHandleAdapter eventHandleAdapter;

  @Bean
  public Consumer<Message<String>> orderSuccess() {
    return eventHandleAdapter::consumerOrderSuccess;
  }
}
