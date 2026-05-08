# Shopping Cart Application

This repository contains the backend service for a comprehensive **E-Commerce Shopping Application**. It is built as a monolithic **RESTful API** using **Spring Boot**. The system is designed to handle user registration, product catalog management, real-time inventory tracking, a secure checkout process, and automated system notifications.

## Features

The application follows a standard layered architecture (**Controller** → **Service** → **Repository** → **Entity**) and is divided into 5 core modules:

1. **User Module**: Manages customer and admin profiles.
2. **Product Module**: Handles the store catalog, categories, and pricing.
3. **Inventory Module**: Tracks available stock and handles low-stock detection.
4. **Checkout/Order Module**: Orchestrates the purchase flow, validates stock, and generates final orders.
5. **Notification Module**: Dispatches system alerts (e.g., Order Confirmations, Low Stock warnings).

## Technology Stack

- **Language**: Java 21
- **Framework**: Spring Boot 4.0.5
- **Web Layer**: Spring Web (REST APIs)
- **Persistence Layer**: Spring Data JPA / Hibernate
- **Database**: MySQL
- **Build Tool**: Maven
- **Validation**: Spring Boot Validation
- **Data Mapping**: ModelMapper 3.2.0
- **Testing**: JUnit 5, Spring Boot Test, H2 (in-memory database for tests)


## API Endpoints

### User Module
- `POST /api/users` - Create a new user
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

### Product Module
- `POST /api/products` - Create a new product
- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

### Inventory Module
- `POST /api/inventory` - Add stock to inventory
- `GET /api/inventory` - Get all inventory items
- `PUT /api/inventory/{id}/update-stock` - Update stock quantity

### Checkout/Order Module
- `POST /api/checkout` - Process checkout and create order
- `GET /api/orders` - Get all orders
- `GET /api/orders/{id}` - Get order by ID
- `GET /api/orders/user/{userId}` - Get orders by user ID

### Notification Module
- `POST /api/notifications` - Create a notification
- `GET /api/notifications` - Get all notifications
- `GET /api/notifications/{id}` - Get notification by ID
- `GET /api/notifications/user/{userId}` - Get notifications by user ID

## Database Schema

The application uses JPA entities with the following main tables:

- **users**: Stores user information (`id`, `name`, `email`, `role`)
- **products**: Stores product details (`productId`, `productName`, `description`, `category`, `price`)
- **inventory**: Tracks product stock (`id`, `product_id`, `quantity`)
- **orders**: Stores order information (`id`, `orderDate`, `status`, `totalAmount`, `user_id`)
- **order_items**: Stores order line items (`id`, `order_id`, `product_id`, `quantity`, `price`)
- **notifications**: Stores system notifications (`id`, `message`, `type`, `user_id`)


## Exception Handling

The application includes global exception handling for:
- Validation errors (**MethodArgumentNotValidException**)
- Resource not found errors (**ResourceNotFoundException**)

