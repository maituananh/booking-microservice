package org.center.infra.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.*;
import org.type.WorkflowStatus;

@Table(name = "workflows")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowEntity {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "workflow")
  private String workflow;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private WorkflowStatus status;

  @Column(name = "current_step")
  private String currentStep;

  @Column(name = "next_step")
  private String nextStep;

  @Column(name = "created_at")
  private Instant createdAt;

  @Column(name = "updated_at")
  private Instant updatedAt;
}
