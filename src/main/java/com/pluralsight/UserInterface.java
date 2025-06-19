// Updated UserInterface.java (with corrected class names and findByVin support)
package com.pluralsight;

import com.pluralsight.dao.*;
import com.pluralsight.models.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.time.LocalDate;
import java.util.*;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private static VehicleDao vehicleDao;
    private static SalesDao salesDao;
    private static LeaseDao leaseDao;

    public UserInterface(VehicleDao vehicleDao, SalesDao salesDao, LeaseDao leaseDao) {
        this.vehicleDao = vehicleDao;
        this.salesDao = salesDao;
        this.leaseDao = leaseDao;
    }
    public static void display() {


        boolean running = true;
        while (running) {
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "                🚘   VEHICLE SEARCH & MANAGEMENT MENU   🚘                        \n" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    " 1  -  Find vehicles by price                                                      \n" +
                    " 2  -  Find vehicles by make/model                                                 \n" +
                    " 3  -  Find vehicles by year range                                                 \n" +
                    " 4  -  Find vehicles by color                                                      \n" +
                    " 5  -  Find vehicles by mileage                                                    \n" +
                    " 6  -  Find vehicles by type                                                       \n" +
                    " 7  -  Add a vehicle                                                               \n" +
                    " 8  -  Remove a vehicle                                                            \n" +
                    " 9  -  Sell or Lease a Vehicle                                                     \n" +
                    " 0  -  Quit                                                                        \n" +
                    "-----------------------------------------------------------------------------------");


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
            System.out.println("\nVIN              | Year  | Make       | Model       | Type         | Color      | Odometer | Price");
            System.out.println("-----------------------------------------------------------------------------------------------");
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
            System.out.println("\nVIN              | Year  | Make       | Model       | Type         | Color      | Odometer | Price");
            System.out.println("-----------------------------------------------------------------------------------------------");
            vehicles.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Invalid year. Please enter a number.");
        }

    }

    private static void searchByYear() {
        try {
            int min = Integer.parseInt(ask("Enter min year: "));
            int max = Integer.parseInt(ask("Enter max year: "));
            List<Vehicle> vehicles = vehicleDao.findByYearRange(min, max);
            System.out.println("\nVIN              | Year  | Make       | Model       | Type         | Color      | Odometer | Price");
            System.out.println("-----------------------------------------------------------------------------------------------");
            vehicles.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchByColor() {
        try {
            String color = ask("Enter color: ");
            List<Vehicle> vehicles = vehicleDao.findByColor(color);
            System.out.println("\nVIN              | Year  | Make       | Model       | Type         | Color      | Odometer | Price");
            System.out.println("-----------------------------------------------------------------------------------------------");
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
            System.out.println("\nVIN              | Year  | Make       | Model       | Type         | Color      | Odometer | Price");
            System.out.println("-----------------------------------------------------------------------------------------------");
            vehicles.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchByType() {
        try {
            String type = ask("Enter type: ");
            List<Vehicle> vehicles = vehicleDao.findByType(type);
            System.out.println("\nVIN              | Year  | Make       | Model       | Type         | Color      | Odometer | Price");
            System.out.println("-----------------------------------------------------------------------------------------------");
            vehicles.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addVehicle() {
        try {
            String vin = ask("VIN: ");
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
