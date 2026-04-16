package com.shoppingapp.main.dto;


import java.util.List;

public class OrderResponseDto {
    private Long orderId;
    private Long userId;
    private Double totalAmount;
    private String orderStatus;
    private List<OrderItemResponseDto> items;

    public OrderResponseDto() {}

    public Long getOrderId() { 
    	return orderId; 
    }
    public void setOrderId(Long orderId) { 
    	this.orderId = orderId; 
    }
    public Long getUserId() { 
    	return userId; 
    }
    public void setUserId(Long userId) { 
    	this.userId = userId; 
    }
    public Double getTotalAmount() { 
    	return totalAmount; 
    }
    public void setTotalAmount(Double totalAmount) { 
    	this.totalAmount = totalAmount; 
    }
    public String getOrderStatus() { 
    	return orderStatus; 
    }
    public void setOrderStatus(String orderStatus) { 
    	this.orderStatus = orderStatus; 
    }
    public List<OrderItemResponseDto> getItems() { 
    	return items; 
    } 
    public void setItems(List<OrderItemResponseDto> items) { 
    	this.items = items; 
    }
}