# 🏥 Hospital Management System

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-Database-CC6699?style=for-the-badge)
![RBAC](https://img.shields.io/badge/RBAC-Enabled-28A745?style=for-the-badge)

</div>

<div align="center">

## 🚀 Smart healthcare management for patients, doctors, and admins

A Java-based hospital management application built to manage patients, appointments, billing, user auth, and role-based access using JDBC and MySQL.

</div>

---

## ✨ Overview

The Hospital Management System is designed to streamline hospital operations by keeping patient records, consultation schedules, billing, and user access organized in one application.

It follows clean object-oriented design principles and a modular layered architecture to make the system maintainable and easier to extend.

---

## 🩺 Features

### Patient Management
- Add patient records
- View patient details
- Update patient information
- Delete patient records

### Appointment Management
- Book appointments
- View appointment records
- Cancel appointments

### Billing Management
- Generate bills
- View billing history
- Update payment status

### Authentication & Access Control
- Secure login
- Role-based access control
- Separate dashboards for admin, doctor, and receptionist

### Database Operations
- JDBC integration with MySQL
- CRUD support
- Structured data handling

---

## 🧩 Tech Stack

| Technology | Purpose |
| --- | --- |
| Java | Core application logic |
| JDBC | Database connectivity |
| MySQL | Data storage and management |
| OOP | Clean software design |
| MVC-style structure | Organizational architecture |
| Exception Handling | Reliable error management |

---

## 🏗️ Architecture

The project follows a layered structure inspired by MVC and DAO patterns:

### Model Layer
- Represents entities such as `User`, `Patient`, `Doctor`, `Appointment`, and `Bill`

### Service Layer
- Contains business logic and validation

### DAO Layer
- Handles database access using JDBC

### Main / Console Layer
- Connects user interaction to system workflows

---

## 🔐 Role-Based Access Control (RBAC)

The system supports role-based login behavior:

- `ADMIN` → full management access
- `DOCTOR` → doctor-specific dashboard
- `RECEPTIONIST` → patient and appointment operations

This is implemented by checking the user role returned from the database after successful login.

---

## 📁 Project Structure

```text
Hospital-Management-System/
│
├── src/
│   ├── main/
│   ├── model/
│   ├── service/
│   ├── dao/
│   ├── util/
│   └── ...
│
├── database/
│   └── hospital.sql
│
├── lib/
│   └── mysql-connector-j-9.7.0.jar
│
├── out/
├── README.md
└── .gitignore
```

---

## ⚙️ Installation & Setup

### Prerequisites
- Java JDK 8 or above
- MySQL Server
- MySQL Workbench or CLI
- IDE such as IntelliJ IDEA or Eclipse

### Steps

1. Clone the repository

```bash
git clone https://github.com/dharshanworks/Hospital-Management-System.git
```

2. Open MySQL Workbench or MySQL CLI.

3. Create the `hospital_db` database and required tables.

4. Insert the default users for login.

5. Configure database credentials in the application.

6. Compile and run:

```powershell
cd "d:\PROJECTS\Hospital Management"
javac -cp "lib/mysql-connector-j-9.7.0.jar" -d out (Get-ChildItem -Path src -Recurse -Filter *.java | Select-Object -ExpandProperty FullName)
java -cp "out;lib/mysql-connector-j-9.7.0.jar" main.HospitalManagementSystem
```

---

## 🧪 Default Login Credentials

```text
admin / admin123
doctor / doctor123
reception / reception123
```

> These credentials are stored in the `users` table and are used to determine the role after successful login.

---

## 🎯 Learning Outcomes

This project strengthens understanding in:
- Java fundamentals
- Database design
- JDBC connectivity
- Role-based access control
- Object-oriented programming
- DAO design patterns
- Software engineering best practices

---

## 🔮 Future Enhancements

- Spring Boot migration
- REST API development
- Web-based frontend
- Docker deployment
- Cloud hosting integration
- Notifications and reports
- Improved analytics dashboard

---

## 👨‍💻 Author

**Dharshan R**

B.Tech Information Technology  
Sri Shakthi Institute of Engineering and Technology

GitHub: https://github.com/dharshanworks

---

<div align="center">

⭐ If you like this project, give it a star and share it with others.

</div>
