package com.shoppingapp.main.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shoppingapp.main.dto.CheckoutRequestDto;
import com.shoppingapp.main.dto.OrderItemRequestDto;
import com.shoppingapp.main.dto.OrderItemResponseDto;
import com.shoppingapp.main.dto.OrderResponseDto;
import com.shoppingapp.main.entity.Order;
import com.shoppingapp.main.entity.OrderItem;
import com.shoppingapp.main.entity.Product;
import com.shoppingapp.main.entity.User;
import com.shoppingapp.main.exception.ResourceNotFoundException;
import com.shoppingapp.main.repository.OrderRepository;
import com.shoppingapp.main.repository.UserRepository;
import com.shoppingapp.main.repository.ProductRepository;

// import com.shoppingapp.main.service.UserService;
// import com.shoppingapp.main.service.ProductService;
// import com.shoppingapp.main.service.InventoryService;
import com.shoppingapp.main.service.NotificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final ModelMapper modelMapper;

	private final UserService userService;
	private final ProductService productService;
	private final InventoryService inventoryService;
	private final NotificationService notificationService;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;

	public OrderServiceImpl(OrderRepository orderRepository, 
			ModelMapper modelMapper,
			UserService userService,
			ProductService productService,
			InventoryService inventoryService,
			NotificationService notificationService, UserRepository userRepository, ProductRepository productRepository) {
		this.orderRepository = orderRepository;
		this.modelMapper = modelMapper;
		this.userService = userService;
		this.productService = productService;
		this.inventoryService = inventoryService;
		this.notificationService = notificationService;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}

	@Override
//	@Transactional
//	public OrderResponseDto createOrder(CheckoutRequestDto request) {
//
//		Order order = new Order();
//
//		// Fetch the real User from the Database via UserService
//		com.shoppingapp.main.dto.UserResponseDto userDto = userService.getUserById(request.getUserId());
//		User user = modelMapper.map(userDto, User.class);
//		order.setUser(user);
//		order.setStatus("CONFIRMED");
//
//		List<OrderItem> items = new ArrayList<>();
//
//		for (OrderItemRequestDto itemRequest : request.getItems()) {
//
//			// Fetch the real Product to get the exact price
//			com.shoppingapp.main.dto.ProductResponseDTO productDto = productService.getProductById(itemRequest.getProductId());
//			Product product = modelMapper.map(productDto, Product.class);
//
//			// Validate and reduce stock via InventoryService
//			inventoryService.validateAndReduceStock(product.getProductId(), itemRequest.getQuantity());
//
//			OrderItem item = new OrderItem();
//			item.setProduct(product);
//			item.setQuantity(itemRequest.getQuantity());
//			item.setPrice(product.getPrice()); 
//
//			items.add(item);
//		}
//
//		order.setItems(items); 
//
//		// Save order to database
//		Order savedOrder = orderRepository.save(order);
//
//		// Trigger Notification via NotificationService
//		notificationService.createOrderNotification(user, savedOrder);
//
//		return modelMapper.map(savedOrder, OrderResponseDto.class);
//	}
	
	
	
	
	@Transactional
	public OrderResponseDto createOrder(CheckoutRequestDto request) {

	    Order order = new Order();

	    User user = userRepository.findById(request.getUserId())
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    order.setUser(user);
	    order.setStatus("CONFIRMED");

	    List<OrderItem> items = new ArrayList<>();
	    double totalAmount = 0;

	    for (OrderItemRequestDto itemRequest : request.getItems()) {

	        Product product = productRepository.findById(itemRequest.getProductId())
	                .orElseThrow(() -> new RuntimeException("Product not found"));

	        inventoryService.validateAndReduceStock(product.getProductId(), itemRequest.getQuantity());

	        OrderItem item = new OrderItem();
	        item.setProduct(product);
	        item.setQuantity(itemRequest.getQuantity());
	        item.setPrice(product.getPrice());

	        totalAmount += product.getPrice() * itemRequest.getQuantity();

	        items.add(item);
	    }

	    order.setItems(items);
	    order.setTotalAmount(totalAmount);

	    Order savedOrder = orderRepository.save(order);

	    notificationService.createOrderNotification(user, savedOrder);

	    return mapToDto(savedOrder);
	}

	@Override
	public List<OrderResponseDto> getAllOrders() {
		return orderRepository.findAll().stream()
				.map(order -> modelMapper.map(order, OrderResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public OrderResponseDto getOrderById(Long id) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
		return modelMapper.map(order, OrderResponseDto.class);
	}

	@Override
	public List<OrderResponseDto> getOrdersByUserId(Long userId) {
		return orderRepository.findByUserId(userId).stream()
				.map(order -> modelMapper.map(order, OrderResponseDto.class))
				.collect(Collectors.toList());
	}
	
	
	//changed
	private OrderResponseDto mapToDto(Order order) {
	    OrderResponseDto dto = new OrderResponseDto();

	    dto.setOrderId(order.getId());
	    dto.setUserId(order.getUser().getId());
	    dto.setOrderStatus(order.getStatus());
	    dto.setTotalAmount(order.getTotalAmount());

	    List<OrderItemResponseDto> itemDtos = order.getItems().stream().map(item -> {
	        OrderItemResponseDto i = new OrderItemResponseDto();
	        i.setProductId(item.getProduct().getProductId());
	        i.setQuantity(item.getQuantity());

	        i.setUnitPrice(item.getPrice());

	        return i;
	    }).toList();

	    dto.setItems(itemDtos);

	    return dto;
	}
}