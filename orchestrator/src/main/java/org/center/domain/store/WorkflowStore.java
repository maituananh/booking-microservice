package org.center.domain.store;

import org.center.domain.entity.Workflow;

public interface WorkflowStore {
  Workflow save(final Workflow workflow);

  Workflow update(final Workflow workflow);
}
