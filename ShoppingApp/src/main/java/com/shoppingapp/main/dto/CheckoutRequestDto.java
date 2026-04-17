package com.shoppingapp.main.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class CheckoutRequestDto {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotEmpty(message = "Order must contain at least one item")
    @Valid
    private List<OrderItemRequestDto> items;

    public CheckoutRequestDto() {}

    public Long getUserId() { 
    	return userId; 
    }
    public void setUserId(Long userId) { 
    	this.userId = userId; 
    }

    public List<OrderItemRequestDto> getItems() { 
    	return items; 
    }
    public void setItems(List<OrderItemRequestDto> items) { 
    	this.items = items; 
    }
}