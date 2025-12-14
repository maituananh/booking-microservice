package org.center.infra.store.impl;

import lombok.RequiredArgsConstructor;
import org.center.domain.entity.Workflow;
import org.center.domain.store.WorkflowStore;
import org.center.infra.mapper.WorkflowInfraMapper;
import org.center.infra.persistence.WorkflowRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WorkflowStoreImpl implements WorkflowStore {

  private final WorkflowRepository workflowRepository;

  @Transactional
  @Override
  public Workflow save(final Workflow workflow) {
    final var entity = WorkflowInfraMapper.toEntity(workflow);
    final var saved = workflowRepository.save(entity);

    return WorkflowInfraMapper.toDomain(saved);
  }

  @Transactional
  @Override
  public Workflow update(final Workflow workflow) {
    final var entity = WorkflowInfraMapper.toEntity(workflow);
    final var saved = workflowRepository.save(entity);

    return WorkflowInfraMapper.toDomain(saved);
  }
}
