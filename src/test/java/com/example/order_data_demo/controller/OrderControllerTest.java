package com.example.order_data_demo.controller;

import com.example.order_data_demo.dto.OrderRequest;
import com.example.order_data_demo.model.Order;
import com.example.order_data_demo.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void submitOrder_Success() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderId(1L);
        orderRequest.setCustomerId(1L);
        orderRequest.setProductIds(1L);
        orderRequest.setTimestamp(Instant.now());

        when(orderService.saveOrder(any(OrderRequest.class))).thenReturn("Order processed!");

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("Order processed!"));
    }

    @Test
    void submitOrder_ProductNotFound() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderId(1L);
        orderRequest.setCustomerId(1L);
        orderRequest.setProductIds(99L);
        orderRequest.setTimestamp(Instant.now());

        when(orderService.saveOrder(any(OrderRequest.class))).thenReturn("Product with id 99 not found.");

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Product with id 99 not found."));
    }

    @Test
    void submitOrder_InvalidDateFormat() throws Exception {
        String invalidJson = "{\"orderId\": 1,\"customerId\": 1,\"productIds\": 1,\"timestamp\": \"2025-08-20T1\"}";

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Text '2025-08-20T1' could not be parsed at index 11")));
    }

    @Test
    void getOrderById_Found() throws Exception {
        Long orderId = 1L;
        Order mockOrder = new Order();
        mockOrder.setOrderId(orderId);

        when(orderService.getOrderById(orderId)).thenReturn(Optional.of(mockOrder));

        mockMvc.perform(get("/api/orders/{id}", orderId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockOrder)));
    }

    @Test
    void getOrderById_NotFound() throws Exception {
        Long orderId = 99L;

        when(orderService.getOrderById(orderId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/orders/{id}", orderId))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }
}
