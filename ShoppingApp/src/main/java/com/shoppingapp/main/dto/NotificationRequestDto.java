package com.shoppingapp.main.dto;

import jakarta.validation.constraints.NotBlank;

public class NotificationRequestDto {

    @NotBlank(message = "Notification type is required")
    private String notificationType;

    @NotBlank(message = "Recipient reference is required")
    private String recipientReference;

    @NotBlank(message = "Message is required")
    private String message;

    public NotificationRequestDto() {}

    public String getNotificationType() { 
    	return notificationType; 
    }
    public void setNotificationType(String notificationType) { 
    	this.notificationType = notificationType; 
    }

    public String getRecipientReference() { 
    	return recipientReference; 
    }
    public void setRecipientReference(String recipientReference) { 
    	this.recipientReference = recipientReference; 
    }

    public String getMessage() { 
    	return message; 
    }
    public void setMessage(String message) { 
    	this.message = message; 
    }
}