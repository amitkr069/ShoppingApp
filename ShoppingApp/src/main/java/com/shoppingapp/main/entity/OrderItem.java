package com.shoppingapp.main.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    // N OrderItems -> 1 Order mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // N OrderItems -> 1 Product mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public OrderItem() {}

    public OrderItem(Integer quantity, Double price, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    // Getters and Setters
    public Long getId() { 
    	return id; 
    }
    public void setId(Long id) { 
    	this.id = id; 
    }

    public Integer getQuantity() { 
    	return quantity; 
    }
    public void setQuantity(Integer quantity) { 
    	this.quantity = quantity; 
    }

    public Double getPrice() { 
    	return price; 
    }
    public void setPrice(Double price) { 
    	this.price = price; 
    }

    public Order getOrder() { 
    	return order; 
    }
    public void setOrder(Order order) { 
    	this.order = order; 
    }

    public Product getProduct() { 
    	return product; 
    }
    public void setProduct(Product product) { 
    	this.product = product; 
    }
}
