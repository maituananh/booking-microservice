package org.booking.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booking.share.type.OrderStatus;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private UUID id;
    private UUID productId;
    private OrderStatus status;
}
