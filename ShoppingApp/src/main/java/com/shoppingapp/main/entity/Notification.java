package com.shoppingapp.main.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String status;

    // N Notifications -> 1 User mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Notification() {}

    public Notification(String type, String message, String status, User user) {
        this.type = type;
        this.message = message;
        this.status = status;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() { 
    	return id; 
    }
    public void setId(Long id) { 
    	this.id = id; 
    }

    public String getType() { 
    	return type; 
    }
    public void setType(String type) { 
    	this.type = type; 
    }

    public String getMessage() { 
    	return message; 
    }
    public void setMessage(String message) { 
    	this.message = message; 
    }

    public String getStatus() { 
    	return status; 
    }
    public void setStatus(String status) { 
    	this.status = status; 
    }

    public User getUser() { 
    	return user; 
    }
    public void setUser(User user) { 
    	this.user = user; 
    }
}