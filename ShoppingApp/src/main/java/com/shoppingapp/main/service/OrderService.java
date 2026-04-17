package com.shoppingapp.main.service;

import java.util.List;

import com.shoppingapp.main.dto.CheckoutRequestDto;
import com.shoppingapp.main.dto.OrderResponseDto;

public interface OrderService {
    OrderResponseDto createOrder(CheckoutRequestDto request);
    List<OrderResponseDto> getAllOrders();
    OrderResponseDto getOrderById(Long id);
    List<OrderResponseDto> getOrdersByUserId(Long userId);
}
