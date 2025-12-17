package org.center.infra.message;

import static org.common.constants.AppConst.*;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.center.domain.entity.Workflow;
import org.center.domain.entity.WorkflowOutbox;
import org.center.domain.store.WorkflowOutboxStore;
import org.center.domain.store.WorkflowStore;
import org.common.type.AggregateType;
import org.common.type.EventType;
import org.common.type.Topic;
import org.common.type.WorkflowStatus;
import org.common.utils.ConvertUtils;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class EventHandleAdapter {

  private final WorkflowStore workflowStore;
  private final WorkflowOutboxStore workflowOutboxStore;

  private final String[] ORDER_WORKFLOW =
      new String[] {ORDER_SERVICE, INVENTORY_SERVICE, PAYMENT_SERVICE};

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

    workflowOutboxStore.save(
        WorkflowOutbox.builder()
            .aggregateId(workflow.getId())
            .aggregateType(AggregateType.INVENTORY)
            .eventId(eventId)
            .topic(Topic.INVENTORY_COMMAND)
            .traceId(traceId)
            .type(EventType.INVENTORY_CREATED)
            .payload(message.getPayload())
            .build());
  }

  @Transactional
  public void handleReserveStockSuccess(final Message<String> message) {
    final var eventType = ConvertUtils.toString(message.getHeaders().get(EVENT_TYPE));
    final var eventId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(EVENT_ID)));
    final var traceId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(TRACE_ID)));

    log.info(
        "handleReserveStockSuccess at Orchestrator: event_type {}, event_id {}, trace_id {}",
        eventType,
        eventId,
        traceId);

    final var consumerInventoryReply = message.getPayload();

    final var workflow =
        workflowStore.save(
            Workflow.builder()
                .status(WorkflowStatus.IN_PROGRESS)
                .currentStep(INVENTORY_SERVICE)
                .nextStep(PAYMENT_SERVICE)
                .workflow(StringUtils.join(ORDER_WORKFLOW, ";"))
                .build());

    workflowOutboxStore.save(
        WorkflowOutbox.builder()
            .aggregateId(workflow.getId())
            .aggregateType(AggregateType.INVENTORY)
            .eventId(eventId)
            .topic(Topic.PAYMENTS_COMMAND)
            .traceId(traceId)
            .type(EventType.PAYMENT_CREATED)
            .payload(message.getPayload())
            .build());
  }

  @Transactional
  public void consumerPayment(final Message<String> message) {
    final var eventType = ConvertUtils.toString(message.getHeaders().get(EVENT_TYPE));
    final var eventId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(EVENT_ID)));
    final var traceId = UUID.fromString(ConvertUtils.toString(message.getHeaders().get(TRACE_ID)));

    log.info(
        "consumerPayment at Orchestrator: event_type {}, event_id {}, trace_id {}",
        eventType,
        eventId,
        traceId);

    final var inventoryPayload = message.getPayload();

    final var workflow =
        workflowStore.save(
            Workflow.builder()
                .status(WorkflowStatus.COMPLETED)
                .currentStep(SUCCESSFUL)
                .nextStep(SUCCESSFUL)
                .workflow(StringUtils.join(ORDER_WORKFLOW, ";"))
                .build());

    workflowOutboxStore.save(
        WorkflowOutbox.builder()
            .aggregateId(workflow.getId())
            .aggregateType(AggregateType.INVENTORY)
            .eventId(eventId)
            .type(EventType.ORDER_SUCCEEDED)
            .topic(Topic.ORDERS_COMMAND)
            .payload(inventoryPayload)
            .traceId(traceId)
            .build());
  }
}
