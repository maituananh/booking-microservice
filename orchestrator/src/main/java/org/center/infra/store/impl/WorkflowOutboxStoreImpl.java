package org.center.infra.store.impl;

import lombok.RequiredArgsConstructor;
import org.center.domain.entity.WorkflowOutbox;
import org.center.domain.store.WorkflowOutboxStore;
import org.center.infra.mapper.WorkflowOutboxInfraMapper;
import org.center.infra.persistence.WorkflowOutboxRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WorkflowOutboxStoreImpl implements WorkflowOutboxStore {

  private final WorkflowOutboxRepository workflowOutboxRepository;

  @Transactional
  @Override
  public WorkflowOutbox save(final WorkflowOutbox workflowOutbox) {
    final var entity = WorkflowOutboxInfraMapper.toEntity(workflowOutbox);
    final var saved = workflowOutboxRepository.save(entity);

    return WorkflowOutboxInfraMapper.toDomain(saved);
  }
}
