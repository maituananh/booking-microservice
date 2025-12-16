package org.center.infra.mapper;

import java.time.Instant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.center.domain.entity.WorkflowOutbox;
import org.center.infra.entity.WorkflowOutboxEntity;
import org.common.type.Topic;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkflowOutboxInfraMapper {

  public static WorkflowOutboxEntity toEntity(final WorkflowOutbox workflowOutbox) {
    return WorkflowOutboxEntity.builder()
        .id(workflowOutbox.getId())
        .topic(workflowOutbox.getTopic().getValue())
        .aggregateType(workflowOutbox.getAggregateType())
        .aggregateId(workflowOutbox.getAggregateId())
        .payload(workflowOutbox.getPayload())
        .type(workflowOutbox.getType())
        .traceId(workflowOutbox.getTraceId())
        .eventId(workflowOutbox.getEventId())
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  public static WorkflowOutbox toDomain(final WorkflowOutboxEntity workflowOutboxEntity) {
    return WorkflowOutbox.builder()
        .id(workflowOutboxEntity.getId())
        .topic(Topic.valueOf(workflowOutboxEntity.getTopic()))
        .aggregateType(workflowOutboxEntity.getAggregateType())
        .aggregateId(workflowOutboxEntity.getAggregateId())
        .payload(workflowOutboxEntity.getPayload())
        .type(workflowOutboxEntity.getType())
        .traceId(workflowOutboxEntity.getTraceId())
        .eventId(workflowOutboxEntity.getEventId())
        .createdAt(workflowOutboxEntity.getCreatedAt())
        .updatedAt(workflowOutboxEntity.getUpdatedAt())
        .build();
  }
}
