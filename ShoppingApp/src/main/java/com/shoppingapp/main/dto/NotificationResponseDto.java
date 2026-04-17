package com.shoppingapp.main.dto;

public class NotificationResponseDto {

	private Long notificationId;
	private String notificationType;
	private String recipientReference;
	private String message;
	private String status;

	public NotificationResponseDto() {}

	public Long getNotificationId() { 
		return notificationId; 
	}
	public void setNotificationId(Long notificationId) { 
		this.notificationId = notificationId; 
	}

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

	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }
}