package org.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Topic {
  ORDERS_COMMAND("orders.command"),
  ORDERS_EVENT("orders.event"),

  PAYMENTS_COMMAND("payments.command"),
  PAYMENTS_EVENT("payments.event"),

  INVENTORY_COMMAND("inventory.command"),
  INVENTORY_EVENT("inventory.event"),
  ;

  private final String value;
}
