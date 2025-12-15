package org.payment.infra.mapper;

import java.time.Instant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.payment.domain.entity.PaymentOutbox;
import org.payment.infra.entity.PaymentOutboxEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentInboxMapper {

  public static PaymentOutboxEntity toEntity(final PaymentOutbox paymentOutbox) {
    return PaymentOutboxEntity.builder()
        .eventId(paymentOutbox.getEventId())
        .aggregateId(paymentOutbox.getAggregateId())
        .aggregateType(paymentOutbox.getAggregateType())
        .traceId(paymentOutbox.getTraceId())
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  public static PaymentOutbox toDomain(final PaymentOutboxEntity entity) {
    return PaymentOutbox.builder()
        .eventId(entity.getEventId())
        .aggregateId(entity.getAggregateId())
        .aggregateType(entity.getAggregateType())
        .traceId(entity.getTraceId())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .build();
  }
}
