package org.booking.infra.mapper;

import java.time.Instant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.booking.domain.entity.OrderOutbox;
import org.booking.infra.entity.OrderOutboxEntity;
import org.common.type.Topic;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderOutboxInfraMapper {

  public static OrderOutboxEntity toEntity(final OrderOutbox orderOutbox) {
    return OrderOutboxEntity.builder()
        .topic(orderOutbox.getTopic().getValue())
        .aggregateId(orderOutbox.getAggregateId())
        .aggregateType(orderOutbox.getAggregateType())
        .type(orderOutbox.getType())
        .payload(orderOutbox.getPayload())
        .traceId(orderOutbox.getTraceId())
        .eventId(orderOutbox.getEventId())
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  public static OrderOutbox toDomain(final OrderOutboxEntity orderOutboxEntity) {
    return OrderOutbox.builder()
        .topic(Topic.valueOf(orderOutboxEntity.getTopic()))
        .aggregateId(orderOutboxEntity.getAggregateId())
        .aggregateType(orderOutboxEntity.getAggregateType())
        .type(orderOutboxEntity.getType())
        .payload(orderOutboxEntity.getPayload())
        .traceId(orderOutboxEntity.getTraceId())
        .build();
  }
}
