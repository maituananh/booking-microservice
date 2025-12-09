package org.booking.infra.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.booking.domain.entity.Order;
import org.booking.domain.entity.OrderOutbox;
import org.booking.infra.entity.OrderEntity;
import org.booking.infra.entity.OrderOutboxEntity;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderInfraMapper {

    public static OrderEntity toEntity(final Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .status(order.getStatus())
                .productId(order.getProductId())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    public static Order toDomain(final OrderEntity orderEntity) {
        return Order.builder()
                .id(orderEntity.getId())
                .status(orderEntity.getStatus())
                .productId(orderEntity.getProductId())
                .build();
    }
}
