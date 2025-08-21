package com.example.order_data_demo.service;

import com.example.order_data_demo.dto.OrderRequest;
import com.example.order_data_demo.model.Customer;
import com.example.order_data_demo.model.Order;
import com.example.order_data_demo.model.Product;
import com.example.order_data_demo.repository.CustomerRepository;
import com.example.order_data_demo.repository.OrderRepository;
import com.example.order_data_demo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
public class OrderService {

  private final ProductRepository productRepository;
  private final CustomerRepository customerRepository;
  private final OrderRepository orderRepository;

  // Use constructor injection for all dependencies
  public OrderService(ProductRepository productRepository,
                      CustomerRepository customerRepository,
                      OrderRepository orderRepository) {
    this.productRepository = productRepository;
    this.customerRepository = customerRepository;
    this.orderRepository = orderRepository;
  }

  public String saveOrder(OrderRequest request) {
    try {
      Product product = productRepository.findById(request.getProductIds())
        .orElseThrow(() -> new EntityNotFoundException("Product with id " + request.getProductIds() + " not found."));

      Customer customer = customerRepository.findById(request.getCustomerId())
        .orElseThrow(() -> new EntityNotFoundException("Customer with id " + request.getCustomerId() + " not found."));

      Order order = new Order(
        request.getOrderId(),
        product.getName(),
        product.getPrice(),
        product.getCategory(),
        product.getTags(),
        customer,
        request.getTimestamp()
      );

      orderRepository.save(order);
      return "Order processed!";
    } catch (EntityNotFoundException e) {
      return e.getMessage();
    }
  }

  public Optional<Order> getOrderById(Long orderId) {
    return orderRepository.findById(orderId);
  }
}
