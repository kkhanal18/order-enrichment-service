package com.example.order_data_demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

  @Id
  private long orderId;

  @Column()
  private String productName;

  @Column()
  private BigDecimal price;

  @Column()
  private String category;

  @Column()
  private String tags;

  @ManyToOne()
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @Column(nullable = false)
  private Instant timestamp;
}
