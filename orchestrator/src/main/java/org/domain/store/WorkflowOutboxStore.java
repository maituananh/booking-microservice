package org.domain.store;

import org.domain.entity.WorkflowOutbox;

public interface WorkflowOutboxStore {

  WorkflowOutbox save(final WorkflowOutbox workflowOutbox);
}
