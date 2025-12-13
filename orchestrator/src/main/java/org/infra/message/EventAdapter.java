package org.infra.message;

import static org.share.consts.Constants.*;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.domain.entity.Workflow;
import org.domain.store.WorkflowOutboxStore;
import org.domain.store.WorkflowStore;
import org.infra.message.dto.OrderMessageRequest;
import org.share.type.WorkflowStatus;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventAdapter {

  private final WorkflowStore workflowStore;
  private final WorkflowOutboxStore workflowOutboxStore;

  private final String[] ORDER_WORKFLOW =
      new String[] {ORDER_SERVICE, INVENTORY_SERVICE, PAYMENT_SERVICE};

  @Transactional
  public void consumerOrderRequest(final Message<OrderMessageRequest> message) {
    final var orderMessageRequest = message.getPayload();

    final var workflow =
        workflowStore.save(
            Workflow.builder()
                .status(WorkflowStatus.IN_PROGRESS)
                .currentStep(ORDER_SERVICE)
                .nextStep(INVENTORY_SERVICE)
                .workflow(StringUtils.join(ORDER_WORKFLOW, ";"))
                .build());

    //    final var outbox = workflowOutboxStore.save(
    //            WorkflowOutbox.builder()
    //                    .aggregateId(message.getHeaders().)
    //                    .aggregateType()
    //                    .build()
    //    );
  }

  @Transactional
  public void consumerInventory(final Message<OrderMessageRequest> message) {
    final var consumerInventoryReply = message.getPayload();

    workflowStore.save(
        Workflow.builder()
            .status(WorkflowStatus.IN_PROGRESS)
            .currentStep(INVENTORY_SERVICE)
            .nextStep(PAYMENT_SERVICE)
            .workflow(StringUtils.join(ORDER_WORKFLOW, ";"))
            .build());
  }

  @Transactional
  public void consumerPayment(final Message<OrderMessageRequest> message) {
    final var consumerInventoryReply = message.getPayload();

    workflowStore.save(
        Workflow.builder()
            .status(WorkflowStatus.IN_PROGRESS)
            .currentStep(PAYMENT_SERVICE)
            .nextStep(PAYMENT_SERVICE)
            .workflow(StringUtils.join(ORDER_WORKFLOW, ";"))
            .build());
  }
}
