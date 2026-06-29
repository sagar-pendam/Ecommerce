# E-Commerce Microservices Backend

A scalable **E-Commerce Backend Application** built using **Spring Boot Microservices Architecture**. The system consists of multiple independent services communicating through REST APIs, Eureka Service Discovery, API Gateway, and Apache Kafka for event-driven communication.

---

## 🚀 Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Cloud
* Spring Security + JWT
* Spring Data JPA
* Hibernate

### Microservices & Cloud

* Eureka Server
* Spring Cloud Config Server
* Spring Cloud Gateway
* OpenFeign

### Database

* MySQL

### Messaging

* Apache Kafka
* Zookeeper

### Containerization

* Docker
* Docker Compose

### Build Tools

* Maven

---

## 📌 Architecture

```text
                        +-------------------+
                        |   API Gateway     |
                        |      (9191)       |
                        +---------+---------+
                                  |
       -----------------------------------------------------
       |            |            |            |             |
       v            v            v            v             v

+--------------+ +--------------+ +--------------+ +--------------+
| Product      | | Inventory    | | Order        | | Payment      |
| Service      | | Service      | | Service      | | Service      |
| (8086)       | | (8085)       | | (8084)       | | (8089)       |
+--------------+ +--------------+ +--------------+ +--------------+

+--------------+ +--------------+ +--------------+ +--------------+
| Auth         | | Cart         | | Review       | | Wishlist     |
| Service      | | Service      | | Service      | | Service      |
| (8087)       | | (9094)       | | (9095)       | | (8091)       |
+--------------+ +--------------+ +--------------+ +--------------+

                 +----------------------------+
                 | Eureka Service Registry    |
                 |          (8761)            |
                 +----------------------------+

                 +----------------------------+
                 | Config Server (8888)       |
                 +----------------------------+

                 +----------------------------+
                 | Kafka + Zookeeper          |
                 +----------------------------+

                 +----------------------------+
                 | MySQL Database             |
                 +----------------------------+
```

---

## 📂 Microservices

| Service                | Port | Description               |
| ---------------------- | ---- | ------------------------- |
| Eureka Server          | 8761 | Service Discovery         |
| Config Server          | 8888 | Centralized Configuration |
| API Gateway            | 9191 | Single Entry Point        |
| Product Service        | 8086 | Product Management        |
| Inventory Service      | 8085 | Stock Management          |
| Order Service          | 8084 | Order Processing          |
| Payment Service        | 8089 | Payment Processing        |
| Authentication Service | 8087 | JWT Authentication        |
| Cart Service           | 9094 | Shopping Cart Management  |
| Review Service         | 9095 | Product Reviews           |
| Wishlist Service       | 8091 | User Wishlist             |

---

## ✨ Features

* Microservices Architecture
* JWT-based Authentication & Authorization
* API Gateway Routing
* Service Discovery using Eureka
* Centralized Configuration
* Event-Driven Communication with Kafka
* Product Management
* Inventory Tracking
* Order Management
* Payment Processing
* Cart Management
* Wishlist Management
* Product Reviews
* Dockerized Deployment

---

## 🐳 Running with Docker

### Clone Repository

```bash
git clone <your-repository-url>
cd ecommerce_microservices
```

### Build All Services

```bash
mvn clean install -DskipTests
```

### Start Containers

```bash
docker compose up --build
```

### Stop Containers

```bash
docker compose down
```

---

## 🌐 Access URLs

| Application      | URL                   |
| ---------------- | --------------------- |
| Eureka Dashboard | http://localhost:8761 |
| Config Server    | http://localhost:8888 |
| API Gateway      | http://localhost:9191 |

---

## 🔐 Authentication

Authentication Service provides:

* User Registration
* User Login
* JWT Token Generation
* Role-Based Authorization

Example Login Endpoint:

```http
POST /auth/login
```

---

## 📡 Kafka Communication

Kafka is used for asynchronous communication between services.

Examples:

* Order Service → Payment Service
* Inventory Service → Order Service
* Payment Service → Refund Events

---

## 🗄️ Database

MySQL is used as the primary database.

Each service maintains its own database/schema.

Examples:

* productdb
* inventorydb
* orderdb
* paymentdb
* authdb
* cartdb
* reviewdb
* favoritedb

---

## 🛠 Future Enhancements

* CI/CD Pipeline
* Kubernetes Deployment
* Redis Caching
* ELK Monitoring
* Distributed Tracing (Zipkin)
* Prometheus + Grafana Monitoring

---

## 👨‍💻 Author

**Sagar Pendam**

* Email: [pendamsagar3@gmail.com](mailto:pendamsagar3@gmail.com)
* GitHub: https://github.com/sagar-pendam

---

## 📄 License

This project is developed for learning and portfolio purposes.
