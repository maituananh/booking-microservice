package org.infra.persistence;

import java.util.UUID;
import org.infra.entity.WorkflowOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowOutboxRepository extends JpaRepository<WorkflowOutboxEntity, UUID> {}
