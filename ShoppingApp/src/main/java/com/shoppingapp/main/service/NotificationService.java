package com.shoppingapp.main.service;

import java.util.List;

import com.shoppingapp.main.dto.NotificationRequestDto;
import com.shoppingapp.main.dto.NotificationResponseDto;
import com.shoppingapp.main.entity.Order;
import com.shoppingapp.main.entity.User;

public interface NotificationService {
    NotificationResponseDto createNotification(NotificationRequestDto request);
    List<NotificationResponseDto> getAllNotifications();
    NotificationResponseDto getNotificationById(Long id);
    List<NotificationResponseDto> getNotificationsByUserId(Long userId);
    void createOrderNotification(User user, Order order);
}