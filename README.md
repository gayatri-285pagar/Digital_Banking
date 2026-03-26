# 💳 Digital Banking System

## 📌 Project Overview

The Digital Banking System is a backend application developed using Spring Boot that allows users to perform basic banking operations such as registration, login, account management, and transactions.
This project demonstrates REST API development, database integration, and layered architecture (Controller, Service, Repository).

---

## 🚀 Features

* 👤 User Registration
* 🔐 User Login Authentication
* 🏦 Account Management (Create / View Account)
* 💸 Transaction Handling (Deposit / Withdraw)
* 📊 Data stored and managed using MySQL database

---

## 🛠️ Tech Stack

* **Language:** Java
* **Framework:** Spring Boot
* **Database:** MySQL
* **Architecture:** REST API
* **Build Tool:** Maven

---

## 📂 Project Structure

```
src/
 ├── controller/    → Handles API requests
 ├── service/       → Contains business logic
 ├── repository/    → Handles database operations
 ├── model/         → Entity classes
 └── resources/     → application.properties
```
---

## 🔗 API Endpoints
---

### 👤 User APIs

* `POST /register` → Register a new user
* `POST /login` → User login authentication
---

### 🏦 Account APIs

* `POST /account/create` → Create a new bank account
* `GET /account/{id}` → Get account details
* `GET /account/balance/{id}` → Check account balance
---

### 💸 Transaction APIs

* `POST /deposit` → Deposit money into account
* `POST /withdraw` → Withdraw money from account
* `POST /transfer` → Transfer money between accounts
* `GET /transactions/{accountId}` → View transaction history
---

### 👨‍💼 Admin APIs

* `GET /admin/users` → Get all users
* `GET /admin/accounts` → Get all accounts
---
---

## ⚙️ How to Run the Project

1. Clone the repository
2. Open the project in STS / IntelliJ
3. Configure database in `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/bank
spring.datasource.username=root
spring.datasource.password=your_password
```

4. Run the Spring Boot application
5. Use Postman to test APIs

---

## 🔐 Security Note

Sensitive files like `application.properties` are excluded using `.gitignore` to protect database credentials.
An example configuration file is provided for reference.

---

## 🎯 Learning Outcomes

* Learned Spring Boot REST API development
* Understood layered architecture (Controller-Service-Repository)
* Gained experience in MySQL database integration
* Practiced Git & GitHub for version control

---

## 📸 Future Improvements

* Add JWT Authentication 🔐
* Integrate frontend using React ⚛️
* Add validation and exception handling
* Deploy project on cloud (AWS / Render)

---

## 👩‍💻 Author

**Gayatri Pagar**
Java | Spring Boot Developer
