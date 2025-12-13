package org.infra.config;

import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.infra.message.EventAdapter;
import org.infra.message.dto.OrderMessageRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

@Configuration
@RequiredArgsConstructor
public class StreamBindingConfig {

  private final EventAdapter eventAdapter;

  @Bean
  public Consumer<Message<OrderMessageRequest>> orderRequest() {
    return eventAdapter::consumerOrderRequest;
  }

  @Bean
  public Consumer<Message<OrderMessageRequest>> inventoryReply() {
    return eventAdapter::consumerInventory;
  }

  @Bean
  public Consumer<Message<OrderMessageRequest>> paymentReply() {
    return eventAdapter::consumerPayment;
  }
}
