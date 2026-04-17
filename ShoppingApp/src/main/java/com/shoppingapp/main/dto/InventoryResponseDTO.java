package com.shoppingapp.main.dto;

public class InventoryResponseDTO {

    private Long id;
    private Integer stockQuantity;
    private Integer threshold;
    private Long productId;

    public InventoryResponseDTO() {}

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