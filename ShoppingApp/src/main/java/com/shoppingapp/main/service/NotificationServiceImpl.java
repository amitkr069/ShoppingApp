package com.shoppingapp.main.service;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.shoppingapp.main.dto.NotificationRequestDto;
import com.shoppingapp.main.dto.NotificationResponseDto;
import com.shoppingapp.main.entity.Notification;
import com.shoppingapp.main.entity.Order;
import com.shoppingapp.main.entity.User;
import com.shoppingapp.main.exception.ResourceNotFoundException;
import com.shoppingapp.main.repository.NotificationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, ModelMapper modelMapper) {
        this.notificationRepository = notificationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public NotificationResponseDto createNotification(NotificationRequestDto request) {
        Notification notification = modelMapper.map(request, Notification.class);
        notification.setStatus("UNREAD");
        
        Notification savedNotification = notificationRepository.save(notification);
        return modelMapper.map(savedNotification, NotificationResponseDto.class);
    }

    @Override
    public List<NotificationResponseDto> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(notification -> modelMapper.map(notification, NotificationResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public NotificationResponseDto getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + id));
        return modelMapper.map(notification, NotificationResponseDto.class);
    }

    @Override
    public List<NotificationResponseDto> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId).stream()
                .map(notification -> modelMapper.map(notification, NotificationResponseDto.class))
                .collect(Collectors.toList());
    }
    
    @Override
    public void createOrderNotification(User user, Order order) {
        Notification notification = new Notification();
        notification.setType("ORDER_CONFIRMED");
        
        String message = "Hello " + user.getName() + ", your order #" + order.getId() + " has been placed successfully!";
        notification.setMessage(message);
        
        notification.setStatus("UNREAD");
        notification.setUser(user);

        notificationRepository.save(notification);
    }
}