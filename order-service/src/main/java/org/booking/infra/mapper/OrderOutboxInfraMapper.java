package org.booking.infra.mapper;

import java.time.Instant;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.booking.domain.entity.OrderOutbox;
import org.booking.infra.entity.OrderOutboxEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderOutboxInfraMapper {

  public static OrderOutboxEntity toEntity(final OrderOutbox orderOutbox) {
    return OrderOutboxEntity.builder()
        .topic(orderOutbox.getTopic())
        .aggregateId(UUID.randomUUID())
        .aggregateType(orderOutbox.getAggregateType())
        .type(orderOutbox.getType())
        .payload(orderOutbox.getPayload())
        .traceId(UUID.randomUUID())
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  public static OrderOutbox toDomain(final OrderOutboxEntity orderOutboxEntity) {
    return OrderOutbox.builder()
        .topic(orderOutboxEntity.getTopic())
        .aggregateId(orderOutboxEntity.getAggregateId())
        .aggregateType(orderOutboxEntity.getAggregateType())
        .type(orderOutboxEntity.getType())
        .payload(orderOutboxEntity.getPayload())
        .traceId(UUID.randomUUID())
        .build();
  }
}
