package org.booking.infra.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.*;
import org.type.OrderStatus;

@Table(name = "orders")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "product_id")
  private UUID productId;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @Column(name = "created_at")
  private Instant createdAt;

  @Column(name = "updated_at")
  private Instant updatedAt;
}
