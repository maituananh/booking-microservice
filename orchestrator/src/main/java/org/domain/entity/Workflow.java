package org.domain.entity;

import java.time.Instant;
import java.util.UUID;
import lombok.*;
import org.type.WorkflowStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workflow {

  private UUID id;
  private String workflow;
  private WorkflowStatus status;
  private String currentStep;
  private String nextStep;
  private Instant createdAt;
  private Instant updatedAt;
}
