package org.infra.mapper;

import java.time.Instant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.domain.entity.WorkflowOutbox;
import org.infra.entity.WorkflowOutboxEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkflowOutboxInfraMapper {

  public static WorkflowOutboxEntity toEntity(final WorkflowOutbox workflowOutbox) {
    return WorkflowOutboxEntity.builder()
        .id(workflowOutbox.getId())
        .topic(workflowOutbox.getTopic())
        .aggregateType(workflowOutbox.getAggregateType())
        .aggregateId(workflowOutbox.getAggregateId())
        .payload(workflowOutbox.getPayload())
        .type(workflowOutbox.getType())
        .traceId(workflowOutbox.getTraceId())
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  public static WorkflowOutbox toDomain(final WorkflowOutboxEntity workflowOutboxEntity) {
    return WorkflowOutbox.builder()
        .id(workflowOutboxEntity.getId())
        .topic(workflowOutboxEntity.getTopic())
        .aggregateType(workflowOutboxEntity.getAggregateType())
        .aggregateId(workflowOutboxEntity.getAggregateId())
        .payload(workflowOutboxEntity.getPayload())
        .type(workflowOutboxEntity.getType())
        .traceId(workflowOutboxEntity.getTraceId())
        .createdAt(workflowOutboxEntity.getCreatedAt())
        .updatedAt(workflowOutboxEntity.getUpdatedAt())
        .build();
  }
}
