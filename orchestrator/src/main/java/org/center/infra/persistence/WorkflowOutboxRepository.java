package org.center.infra.persistence;

import java.util.UUID;
import org.center.infra.entity.WorkflowOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowOutboxRepository extends JpaRepository<WorkflowOutboxEntity, UUID> {}
