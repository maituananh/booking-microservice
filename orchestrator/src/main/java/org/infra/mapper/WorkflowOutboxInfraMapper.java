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
        .status(workflowOutbox.getStatus())
        .workflow(workflowOutbox.getWorkflow())
        .currentStep(workflowOutbox.getCurrentStep())
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  public static WorkflowOutbox toDomain(final WorkflowOutboxEntity workflowOutboxEntity) {
    return WorkflowOutbox.builder()
        .id(workflowOutboxEntity.getId())
        .status(workflowOutboxEntity.getStatus())
        .workflow(workflowOutboxEntity.getWorkflow())
        .currentStep(workflowOutboxEntity.getCurrentStep())
        .createdAt(workflowOutboxEntity.getCreatedAt())
        .updatedAt(workflowOutboxEntity.getUpdatedAt())
        .build();
  }
}
