package org.center.domain.store;

import org.center.domain.entity.WorkflowOutbox;

public interface WorkflowOutboxStore {

  WorkflowOutbox save(final WorkflowOutbox workflowOutbox);
}
