package org.payment.infra.mapper;

import java.time.Instant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.payment.domain.entity.PaymentOutbox;
import org.payment.infra.entity.PaymentOutboxEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentOutboxMapper {

  public static PaymentOutboxEntity toEntity(final PaymentOutbox domain) {
    return PaymentOutboxEntity.builder()
        .id(domain.getId())
        .type(domain.getType())
        .topic(domain.getTopic())
        .aggregateId(domain.getAggregateId())
        .aggregateType(domain.getAggregateType())
        .payload(domain.getPayload())
        .traceId(domain.getTraceId())
        .eventId(domain.getEventId())
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  public static PaymentOutbox toDomain(final PaymentOutboxEntity entity) {
    return PaymentOutbox.builder()
        .id(entity.getId())
        .type(entity.getType())
        .topic(entity.getTopic())
        .aggregateId(entity.getAggregateId())
        .aggregateType(entity.getAggregateType())
        .payload(entity.getPayload())
        .traceId(entity.getTraceId())
        .eventId(entity.getEventId())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .build();
  }
}
