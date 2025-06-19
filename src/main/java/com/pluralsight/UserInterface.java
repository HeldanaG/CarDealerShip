// Updated UserInterface.java (with corrected class names and findByVin support)
package com.pluralsight;

import com.pluralsight.dao.*;
import com.pluralsight.models.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private static VehicleDao vehicleDao;
    private static SalesDao salesDao;
    private static LeaseDao leaseDao;

    public static void display(BasicDataSource dataSource) {
        vehicleDao = new VehicleDao(dataSource);
        salesDao = new SalesDao(dataSource);
        leaseDao = new LeaseDao(dataSource);

        boolean running = true;
        while (running) {
            System.out.println("\n=== Vehicle Dealership Menu ===");
            System.out.println("1. Search by price range");
            System.out.println("2. Search by make/model");
            System.out.println("3. Search by year range");
            System.out.println("4. Search by color");
            System.out.println("5. Search by mileage range");
            System.out.println("6. Search by type");
            System.out.println("7. Add vehicle");
            System.out.println("8. Remove vehicle");
            System.out.println("9. Sell/Lease vehicle");
            System.out.println("0. Exit");

            String option = ask("Choose option: ");
            switch (option) {
                case "1" -> searchByPrice();
                case "2" -> searchByMakeModel();
                case "3" -> searchByYear();
                case "4" -> searchByColor();
                case "5" -> searchByMileage();
                case "6" -> searchByType();
                case "7" -> addVehicle();
                case "8" -> removeVehicle();
                case "9" -> processContract();
                case "0" -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void searchByPrice() {
        try {
            double min = Double.parseDouble(ask("Enter minimum price: "));
            double max = Double.parseDouble(ask("Enter maximum price: "));
            List<Vehicle> vehicles = vehicleDao.findByPriceRange(min, max);
            vehicles.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchByMakeModel() {
        try {
            String make = ask("Enter make: ");
            String model = ask("Enter model: ");
            List<Vehicle> vehicles = vehicleDao.findByMakeModel(make, model);
            vehicles.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchByYear() {
        try {
            int min = Integer.parseInt(ask("Enter min year: "));
            int max = Integer.parseInt(ask("Enter max year: "));
            List<Vehicle> vehicles = vehicleDao.findByYearRange(min, max);
            vehicles.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchByColor() {
        try {
            String color = ask("Enter color: ");
            List<Vehicle> vehicles = vehicleDao.findByColor(color);
            vehicles.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchByMileage() {
        try {
            int min = Integer.parseInt(ask("Enter min mileage: "));
            int max = Integer.parseInt(ask("Enter max mileage: "));
            List<Vehicle> vehicles = vehicleDao.findByMileageRange(min, max);
            vehicles.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchByType() {
        try {
            String type = ask("Enter type: ");
            List<Vehicle> vehicles = vehicleDao.findByType(type);
            vehicles.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addVehicle() {
        try {
            int vin = Integer.parseInt(ask("VIN: "));
            int year = Integer.parseInt(ask("Year: "));
            String make = ask("Make: ");
            String model = ask("Model: ");
            String type = ask("Type: ");
            String color = ask("Color: ");
            int odometer = Integer.parseInt(ask("Odometer: "));
            double price = Double.parseDouble(ask("Price: "));

            Vehicle v = new Vehicle(vin, year, make, model, type, color, odometer, price);
            vehicleDao.addVehicle(v);
            System.out.println("Vehicle added.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void removeVehicle() {
        try {
            String vin = ask("Enter VIN to remove: ");
            boolean removed = vehicleDao.deleteVehicle(vin);
            System.out.println(removed ? "Removed successfully." : "VIN not found.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void processContract() {
        try {
            String vin = ask("Enter VIN to process: ");
            Vehicle vehicle = vehicleDao.findByVin(vin);
            if (vehicle == null) {
                System.out.println("Vehicle not found.");
                return;
            }
            String name = ask("Customer Name: ");
            String email = ask("Customer Email: ");
            String type = ask("Contract type (SALE/LEASE): ").toUpperCase();

            if (type.equals("SALE")) {
                boolean finance = ask("Finance? (yes/no): ").equalsIgnoreCase("yes");
                SalesContract contract = new SalesContract(vehicle, LocalDate.now(), name, email, 100.0, 295.0, finance);
                salesDao.addSales(contract);
            } else if (type.equals("LEASE")) {
                double leaseFee = 300.0;
                double endValue = vehicle.getPrice() * 0.5;
                LeaseContract contract = new LeaseContract(vehicle, LocalDate.now(), name, email, leaseFee, endValue);
                leaseDao.addLease(contract);
            } else {
                System.out.println("Invalid contract type.");
                return;
            }

            vehicleDao.deleteVehicle(vin);
            System.out.println("Contract processed and vehicle removed.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static String ask(String q) {
        System.out.print(q);
        return scanner.nextLine().trim();
    }
}
