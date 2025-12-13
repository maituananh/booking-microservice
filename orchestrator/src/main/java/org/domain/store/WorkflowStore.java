package org.domain.store;

import org.domain.entity.Workflow;

public interface WorkflowStore {
  Workflow save(final Workflow workflow);

  Workflow update(final Workflow workflow);
}
