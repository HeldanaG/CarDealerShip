# 🚗 Dealership Management System 🚗

A Java-based inventory and contract management system designed to support multiple dealerships. Each dealer can manage their vehicle inventory, process customer sales and lease contracts, and retain detailed, dealership-specific records. This application is ideal for leasing to individual dealerships, offering them a self-contained digital solution.

## 📌 Features

### ✅ Inventory Management (Per Dealer)

- Every dealership creates its own vehicle inventory file, e.g. PrimeAuto.csv

- Add, remove, and view all vehicles

- Search vehicles by:

    - Price range

    - Make & Model

    - Year

    - Color

    - Mileage

    - Type (SUV, Car, Truck, etc.)

### ✅ Customer Sales & Leasing

- Process vehicle sales or leases

- Financing logic based on price tiers

- Lease eligibility restricted to newer vehicles (≤ 3 years old)

- After each contract:

  - A receipt is printed

  - A contract file is created per dealer, e.g. PrimeAutoContracts.csv

### ✅ Multi-Dealer Support

- Each dealer gets:

    - 1 inventory file: DealerName.csv

    - 1 contracts file: DealerNameContracts.csv

    - Keeps records isolated per dealership, allowing easy distribution of the software to multiple businesses

### ✅ User & Dealership Setup

- Dealership profile creation (name, address, phone)

- First-time and returning login logic

- Saves and loads each dealership’s own vehicle inventory and contracts

## 💻 How It Works

- When the program starts, the dealer either logs in or registers

- Their own inventory file is loaded and displayed

- Sales and leases are processed via a menu interface

- All contracts and vehicle changes are saved per-dealer

## 🧱 Project Structure

```
src/main/java/com/pluralsight/
├── Vehicle.java
├── Dealership.java
├── DealershipFileManager.java
├── Contract.java (abstract)
├── SalesContract.java
├── LeaseContract.java
├── ContractFileManager.java
└── UserInterface.java
```
## 📸 Screenshots

📍 Dealer Set Up UI

![image](https://github.com/user-attachments/assets/a8d22b15-f56f-4de7-a4e1-92d7f404c2dd)

📍 Menu UI

![image](https://github.com/user-attachments/assets/6a957fff-e7a1-4049-b0b1-b15f8a3739e5)

📍 Vehicle Search Result

![image](https://github.com/user-attachments/assets/61e9c040-d357-4e08-8602-fc1cb8245a5f)

📍 Sale/Lease Processing

Screenshot of: processSellOrLeaseVehicleRequest() interaction with buyer

📍 Receipt

Screenshot of: Receipt printed inside ContractFileManager.saveContract() method


## 🛠️ Technologies Used

- Java (JDK 17+)

- Object-Oriented Programming (Inheritance, Abstraction)

- File I/O with BufferedReader and BufferedWriter

- Console-based UI (extendable to GUI)

## 🚀 Getting Started

1. Clone the Repository
```
git clone https://github.com/your-username/dealership-management.git
cd dealership-management
```
2. Open in IntelliJ (or your IDE of choice)

Make sure your ``` src/main/resources ``` folder exists for saving CSV files.

3. Run the Program.java file

Run > Program.main()

4. Follow the Console Prompts

Create or log in to your dealership and begin managing your inventory!

## 📦 Dealer-Specific Files Created

- Each dealer receives:

    - 📄 DealerName.csv → Vehicle Inventory

    - 📄 DealerNameContracts.csv → Sales & Lease Contracts

- This makes it easy to lease the software to multiple dealerships while keeping their records separate and organized.

## 🔮 Future Enhancements

- Admin dashboard to view all contracts

- GUI version using JavaFX or Swing

- Integration with a database (SQLite/MySQL)

## 🙋‍♀️ Author

Heldana Gebremariam
Java Developer & System Designer
05/23/2025
