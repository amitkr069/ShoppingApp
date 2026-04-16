package com.shoppingapp.main.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class InventoryDTO {

    private Long id;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stockQuantity;

    @NotNull(message = "Threshold is required")
    @Min(value = 1, message = "Threshold must be at least 1")
    private Integer threshold;

    private Long productId;

    public InventoryDTO() {}

    public Long getId() { 
    	return id; 
    }
    public void setId(Long id) { 
    	this.id = id; 
    }

    public Integer getStockQuantity() { 
    	return stockQuantity; 
    	}
    public void setStockQuantity(Integer stockQuantity) { 
    	this.stockQuantity = stockQuantity; 
    	}

    public Integer getThreshold() { 
    	return threshold; 
    	}
    public void setThreshold(Integer threshold) { 
    	this.threshold = threshold; 
    	}

    public Long getProductId() { 
    	return productId; 
    	}
    public void setProductId(Long productId) { 
    	this.productId = productId; 
    	}
}