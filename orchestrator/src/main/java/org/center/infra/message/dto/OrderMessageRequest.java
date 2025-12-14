package org.center.infra.message.dto;

import java.util.UUID;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderMessageRequest {
  private UUID id;
}
