package com.shoppingapp.main.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    private String description;

    private String category;

    @Column(nullable = false)
    private Double price;

    
    //One Product -> Many OrderItems
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore   // prevents infinite loop
    private List<OrderItem> orderItems;

  
    //One Product -> One Inventory
    
    @ManyToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore   // prevents infinite loop
    private Inventory inventory;

    // Constructors
    public Product() {}

    public Product(Long productId, String productName, String description, String category, Double price) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}