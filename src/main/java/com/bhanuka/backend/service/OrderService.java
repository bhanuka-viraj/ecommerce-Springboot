package com.bhanuka.backend.service;

import com.bhanuka.backend.dto.OrderDto;
import com.bhanuka.backend.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<Order> getAllOrders();
    Order createOrder(OrderDto orderDto);
    Order getOrderById(Long id);
}
