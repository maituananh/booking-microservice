package org.center.infra.mapper;

import java.time.Instant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.center.domain.entity.Workflow;
import org.center.infra.entity.WorkflowEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkflowInfraMapper {

  public static WorkflowEntity toEntity(final Workflow workflow) {
    return WorkflowEntity.builder()
        .id(workflow.getId())
        .status(workflow.getStatus())
        .workflow(workflow.getWorkflow())
        .currentStep(workflow.getCurrentStep())
        .nextStep(workflow.getNextStep())
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  public static Workflow toDomain(final WorkflowEntity workflowEntity) {
    return Workflow.builder()
        .id(workflowEntity.getId())
        .status(workflowEntity.getStatus())
        .workflow(workflowEntity.getWorkflow())
        .currentStep(workflowEntity.getCurrentStep())
        .nextStep(workflowEntity.getNextStep())
        .createdAt(workflowEntity.getCreatedAt())
        .updatedAt(workflowEntity.getUpdatedAt())
        .build();
  }
}
