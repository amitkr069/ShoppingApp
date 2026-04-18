package com.shoppingapp.main.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.shoppingapp.main.dto.CheckoutRequestDto;
import com.shoppingapp.main.dto.OrderItemRequestDto;
import com.shoppingapp.main.dto.OrderResponseDto;
import com.shoppingapp.main.dto.ProductResponseDTO;
import com.shoppingapp.main.dto.UserResponseDto;
import com.shoppingapp.main.entity.Order;
import com.shoppingapp.main.entity.Product;
import com.shoppingapp.main.entity.User;
import com.shoppingapp.main.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private UserService userService;
    @Mock
    private ProductService productService;
    @Mock
    private InventoryService inventoryService;
    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void testCreateOrder_Success() {
        // Arrange
        CheckoutRequestDto request = new CheckoutRequestDto();

        request.setUserId(1L);

        List<OrderItemRequestDto> items = new ArrayList<>();

        OrderItemRequestDto item = new OrderItemRequestDto();

        item.setProductId(100L);
        item.setQuantity(2);
        items.add(item);
        request.setItems(items);

        UserResponseDto userDto = new UserResponseDto();
        User user = new User();

        ProductResponseDTO productDto = new ProductResponseDTO();
        productDto.setProductId(100L);
        productDto.setPrice(50.0);

        Product product = new Product();
        product.setProductId(100L);
        product.setPrice(50.0);

        Order savedOrder = new Order();
        savedOrder.setId(10L);

        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setOrderId(10L);

        when(userService.getUserById(1L)).thenReturn(userDto);

        when(modelMapper.map(userDto, User.class)).thenReturn(user);

        when(productService.getProductById(100L)).thenReturn(productDto);

        when(modelMapper.map(productDto, Product.class)).thenReturn(product);

        when(inventoryService.validateAndReduceStock(100L, 2)).thenReturn(true);

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        doNothing().when(notificationService).createOrderNotification(any(User.class), any(Order.class));
        when(modelMapper.map(savedOrder, OrderResponseDto.class)).thenReturn(responseDto);

        // Act
        OrderResponseDto result = orderService.createOrder(request);

        // Assert
        assertNotNull(result);
        assertEquals(10L, result.getOrderId());

        verify(orderRepository, times(1)).save(any(Order.class));
        
        verify(notificationService, times(1)).createOrderNotification(any(User.class), any(Order.class));
    }
}
