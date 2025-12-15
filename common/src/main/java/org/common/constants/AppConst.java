package org.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppConst {

  public static final String EVENT_TYPE = "eventType";

  public static final String EVENT_ID = "eventId";

  public static final String AGGREGATE_ID = "aggregateId";

  public static final String AGGREGATE_TYPE = "aggregateType";

  public static final String TRACE_ID = "traceId";

  public static final String ORDER_SERVICE = "ORDER_SERVICE";

  public static final String INVENTORY_SERVICE = "INVENTORY_SERVICE";

  public static final String PAYMENT_SERVICE = "PAYMENT_SERVICE";

  public static final String SUCCESSFUL = "SUCCESSFUL";
}
