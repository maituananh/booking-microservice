package org.infra.message;

import static org.constants.AppConst.*;
import static org.constants.AppConst.TRACE_ID;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.domain.entity.Workflow;
import org.domain.entity.WorkflowOutbox;
import org.domain.store.WorkflowOutboxStore;
import org.domain.store.WorkflowStore;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.type.AggregateType;
import org.type.Topic;
import org.type.WorkflowStatus;
import org.utils.ConvertUtils;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class EventHandleAdapter {

  private final WorkflowStore workflowStore;
  private final WorkflowOutboxStore workflowOutboxStore;

  private final String[] ORDER_WORKFLOW = new String[] {ORDER_SERVICE, INVENTORY_SERVICE};

  @Transactional
  public void consumerOrder(final Message<String> message) {
    final var eventType = ConvertUtils.toString(message.getHeaders().get(EVENT_TYPE));
    final var eventId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(EVENT_ID)));
    final var traceId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(TRACE_ID)));

    log.info(
        "EventHandlerAdapter at Orchestrator: event_type {}, event_id {}, trace_id {}",
        eventType,
        eventId,
        traceId);

    final var workflow =
        workflowStore.save(
            Workflow.builder()
                .status(WorkflowStatus.IN_PROGRESS)
                .currentStep(ORDER_SERVICE)
                .nextStep(INVENTORY_SERVICE)
                .workflow(StringUtils.join(ORDER_WORKFLOW, ";"))
                .build());

    final var outbox =
        workflowOutboxStore.save(
            WorkflowOutbox.builder()
                .aggregateId(UUID.randomUUID())
                .aggregateType(AggregateType.INVENTORY)
                .eventId(eventId)
                .topic(Topic.ORDER_RESERVE_STOCK_INVENTORY)
                .traceId(traceId)
                .type(eventType)
                .build());
  }

  @Transactional
  public void consumerInventory(final Message<String> message) {
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
  public void consumerPayment(final Message<String> message) {
    final var consumerInventoryReply = message.getPayload();

    workflowStore.save(
        Workflow.builder()
            .status(WorkflowStatus.IN_PROGRESS)
            .currentStep(PAYMENT_SERVICE)
            .nextStep(PAYMENT_SERVICE)
            .workflow(StringUtils.join(ORDER_WORKFLOW, ";"))
            .build());
  }

  @Transactional
  public void consumerOrderSuccess(final Message<String> message) {
    final var eventType = ConvertUtils.toString(message.getHeaders().get(EVENT_TYPE));
    final var eventId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(EVENT_ID)));
    final var traceId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(TRACE_ID)));

    final var consumerInventoryReply = message.getPayload();

    workflowStore.save(
        Workflow.builder()
            .status(WorkflowStatus.COMPLETED)
            .currentStep(SUCCESSFUL)
            .nextStep(SUCCESSFUL)
            .workflow(StringUtils.join(ORDER_WORKFLOW, ";"))
            .build());

    final var outbox =
        workflowOutboxStore.save(
            WorkflowOutbox.builder()
                .aggregateId(UUID.randomUUID())
                .aggregateType(AggregateType.INVENTORY)
                .eventId(eventId)
                .type(eventType)
                .topic(Topic.ORDER_SUCCESS)
                .traceId(traceId)
                .build());
  }
}
