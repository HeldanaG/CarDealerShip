# 🚗 Dealership Management System (SQL Version)

A Java-based inventory and contract management system that supports multiple dealerships with full **MySQL database integration**. Designed for scalability and clean dealership-specific separation, it allows businesses to manage vehicle inventory, handle lease and sales contracts, and securely store all dealership activity in a relational database.

---

## 📌 Features

### ✅ Inventory Management
- Add, remove, and view vehicles from the database
- Search inventory by:
  - Price range
  - Make & Model
  - Year range
  - Color
  - Mileage
  - Type (e.g., SUV, Car, Truck)

### ✅ Sales & Lease Contracts
- Create vehicle **sales** or **lease** contracts
- Finance logic based on vehicle price
- Lease logic includes depreciation, interest, and ending value
- All contracts are saved to `SalesContracts` or `LeaseContracts` MySQL tables

### ✅ Multi-Dealer Support
- Dealerships stored in `dealerships` table
- Each dealership linked to vehicles via `inventory` table
- VIN-based integrity maintained through **foreign key constraints**

---

## 🧱 Project Structure
```
src/main/java/com/pluralsight/
├── models/
│ ├── Vehicle.java
│ ├── Contract.java
│ ├── SalesContract.java
│ ├── LeaseContract.java
│ ├── Dealership.java
├── dao/
│ ├── VehicleDao.java
│ ├── SalesDao.java
│ ├── LeaseDao.java
├── Program.java
└── UserInterface.java
```

---

## 🛠️ Technologies Used

- Java 17+
- MySQL 8+
- Apache Commons DBCP (for connection pooling)
- JDBC (Java Database Connectivity)
- Object-Oriented Programming

---

## 💻 How to Run

### 1. Set Up MySQL
- Ensure MySQL is installed and running.
- Import the `CarDealerShip.sql` script to create the database and sample data.

### 2. Configure Credentials
Update your `Program.java`:

```java
dataSource.setUrl("jdbc:mysql://localhost:3306/CarDealerShip");
dataSource.setUsername("your_username");
dataSource.setPassword("your_password");
```

## 📦 Tables Used

- dealerships
- vehicles
- inventory
- SalesContracts
- lease_contracts

Each table is pre-populated with sample data for testing.

## 📸 Screenshots

![image](https://github.com/user-attachments/assets/467f50e6-c688-4585-b0ad-61f64392edd5)

![image](https://github.com/user-attachments/assets/d90981cd-0fd1-41ef-a411-0b4dfc619025)

![image](https://github.com/user-attachments/assets/279dc9ea-7f2d-478a-95be-2c89fe079190)

![image](https://github.com/user-attachments/assets/798451db-8d40-463e-a0c3-99411b7a6e61)

## 🙋‍♀️ Author
Heldana Gebremariam

Software Developer | System Designer


