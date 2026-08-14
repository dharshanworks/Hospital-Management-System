# Hospital Management System

## Overview

Hospital Management System is a Java-based application designed to streamline hospital operations by managing patients, appointments, billing, and user authentication. The system provides a structured and secure approach to handling healthcare data while ensuring efficient database management through JDBC and MySQL integration.

The project follows Object-Oriented Programming (OOP) principles and the MVC (Model-View-Controller) architecture to create a maintainable, scalable, and organized application.

---

## Features

### Patient Management

* Add new patient records
* View patient details
* Update patient information
* Delete patient records

### Appointment Management

* Schedule appointments
* View appointment history
* Manage appointment records

### Billing Management

* Generate patient bills
* View billing information
* Update billing records

### User Authentication

* Secure login functionality
* Role-Based Access Control (RBAC)
* Separate access levels for different users

### Database Operations

* JDBC-based database connectivity
* CRUD (Create, Read, Update, Delete) operations
* Reliable data storage using MySQL

---

## Tech Stack

| Technology         | Purpose                      |
| ------------------ | ---------------------------- |
| Java               | Core Application Development |
| JDBC               | Database Connectivity        |
| MySQL              | Relational Database          |
| OOP                | Software Design Principles   |
| MVC Architecture   | Application Structure        |
| Exception Handling | Error Management             |

---

## Architecture

The project follows the **MVC Architecture**:

### Model Layer

* Represents business entities such as Patients, Appointments, Bills, and Users.

### View Layer

* Handles user interaction through the console interface.

### Controller Layer

* Processes user requests and coordinates between the View and Service layers.

### Service Layer

* Contains business logic and validation.

### DAO Layer

* Manages database operations using JDBC.

---

## Key Concepts Implemented

* Object-Oriented Programming (OOP)
* Encapsulation
* Inheritance
* Polymorphism
* Abstraction
* MVC Architecture
* DAO Design Pattern
* JDBC Connectivity
* Exception Handling
* Role-Based Access Control (RBAC)

---

## Project Structure

```text
Hospital-Management-System/
│
├── src/
│   ├── model/
│   ├── controller/
│   ├── service/
│   ├── dao/
│   ├── util/
│   └── main/
│
├── database/
│   └── hospital_db.sql
│
└── README.md
```

---

## Installation & Setup

### Prerequisites

* Java JDK 8 or above
* MySQL Server
* IDE (IntelliJ IDEA / Eclipse)

### Steps

1. Clone the repository

```bash
git clone https://github.com/dharshanworks/Hospital-Management-System.git
```

2. Create a MySQL database.

3. Import the database schema.

4. Configure database credentials in the JDBC configuration file.

5. Compile and run the application.

---

## Learning Outcomes

Through this project, the following concepts were strengthened:

* Java Programming Fundamentals
* Database Design and Management
* JDBC Connectivity
* MVC Architecture
* DAO Design Pattern
* Authentication and Authorization
* Exception Handling
* Software Engineering Best Practices

---

## Future Enhancements

* Spring Boot Integration
* RESTful APIs
* Web-Based User Interface
* Docker Deployment
* Cloud Integration (AWS)
* Email and SMS Notifications
* Report Generation

---

## Author

**Dharshan R**
B.Tech Information Technology
Sri Shakthi Institute of Engineering and Technology

GitHub: https://github.com/dharshanworks
