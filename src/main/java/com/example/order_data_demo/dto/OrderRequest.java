package com.example.order_data_demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
  private long orderId;
  private long customerId;
  private long productIds;
  private Instant timestamp;
}
