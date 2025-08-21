package com.example.order_data_demo.dto;

import com.example.order_data_demo.model.Customer;
import com.example.order_data_demo.model.Product;
import lombok.Value;

import java.time.Instant;

@Value
public class EnrichedOrder {
  long orderId;
  Instant timestamp;
  Customer customer;
  Product product;
}
