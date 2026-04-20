Shopping Cart App

This repository contains the backend service for a comprehensive E-Commerce Shopping Application. It is built as a monolithic RESTful API using Spring Boot. The system is designed to handle user registration, product catalog management, real-time inventory tracking, a secure checkout process, and automated system notifications.

Language: Java 21
Framework: Spring Boot
Web Layer: Spring Web (REST APIs)
Persistence Layer: Spring Data JPA / Hibernate
Database: MySQL or PostgreSQL 
Build Tool: Maven
Validation: Spring Boot Validation
Data Mapping: ModelMapper
Testing: Postman (API Endpoints)

The application follows a standard layered architecture (`Controller` -> `Service` -> `Repository` -> `Entity`/`DB`) and is divided into 5 core modules:

1. User Module: Manages customer and admin profiles.
2. Product Module: Handles the store catalog, categories, and pricing.
3. Inventory Module: Tracks available stock and handles low-stock detection.
4. Checkout/Order Module: Orchestrates the purchase flow, validates stock, and generates final orders.
5. Notification Module: Dispatches system alerts (e.g., Order Confirmations, Low Stock warnings).


 JDK 21 installed
 Maven installed
 MySQL or PostgreSQL server running locally
 IDE (Eclipse STS)
 Git

