package com.pluralsight;

import com.pluralsight.models.*;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;
public class UserInterface {

    private static Dealership newDealer;
    static Scanner scanner = new Scanner(System.in);
    private static String fileName;
    private UserInterface() {}

    public static void display() {
        if (!verifyDealership()) {
            System.out.println("Access denied. Exiting application.");
            return;
        }
        loadDealership();
        boolean menuRunning = true;

        while (menuRunning) {
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                                "                          🚘 " + newDealer.getDealerName().toUpperCase() + "'S 🚘 \n"+
                                "                  VEHICLE SEARCH & MANAGEMENT MENU                           \n" +
                                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                                " 1  -  Find vehicles by price                                                      \n" +
                                " 2  -  Find vehicles by make/model                                                 \n" +
                                " 3  -  Find vehicles by year range                                                 \n" +
                                " 4  -  Find vehicles by color                                                      \n" +
                                " 5  -  Find vehicles by mileage                                                    \n" +
                                " 6  -  Find vehicles by type                                                       \n" +
                                " 7  -  List all vehicles                                                           \n" +
                                " 8  -  Add a vehicle                                                               \n" +
                                " 9  -  Remove a vehicle                                                            \n" +
                                " 10 -  Sell or Lease a Vehicle                                                     \n" +
                                " 99  -  Quit                                                                        \n" +
                                "-----------------------------------------------------------------------------------");

            int option = -1;
            while (true) {
                System.out.print("Enter option: ");
                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine();
                }
            }
            timer(700);
            switch (option) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 10 -> processSellOrLeaseVehicleRequest();
                case 99 -> {
                    System.out.println("Goodbye!");
                    menuRunning = false;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static boolean verifyDealership() {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "                     🏢 DEALERSHIP VERIFICATION REQUIRED                             \n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        String response = askQuestion("Have you used the Vehicle Search & Management System before? (yes/no): ").toLowerCase();

        if (response.equals("yes")) {
            fileName = askQuestion("Enter your dealership file name (e.g., Raddison.csv): ");
            File file = new File("src/main/resources/" + fileName);

            if (file.exists()) {
                return authenticateUser();
            } else {
                System.out.println("File not found. Please check the name and try again.");
                return false;
            }
        } else if (response.equals("no")) {
            System.out.println("\nLet's set up your dealership profile.");
            String name = capitalizeWords(getValidString("Enter dealership name: "));
            String address = capitalizeWords(getValidString("Enter dealership address: "));
            String phone = getValidatedNumber("Enter dealership phone number: ");
            newDealer = new Dealership(name, address, phone);

            try {
                fileName = name.replace(" ", "_").toLowerCase() + ".csv";
                String filePath = "src/main/resources/" + fileName;
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                writer.write("-------------------------- Dealership Information ----------------------------------\n");
                writer.write("Name | Address | Phone\n");
                writer.write(name + "|" + address + "|" + phone + "\n");
                writer.write("-----------------------------------------------------------------------------------\n\n");
                writer.write("---------------------------- Vehicle Inventory -------------------------------------\n");
                writer.write(String.format("%-8s | %-6s | %-10s | %-12s | %-12s | %-10s | %-8s | %-11s\n",
                        "VIN", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price"));
                writer.write("-----------------------------------------------------------------------------------\n");
                writer.close();
                System.out.println("Dealership account created successfully.");
                System.out.println("log-in credentials created for your account, user name will be vsm and password will be vsm123 ");

                return true;
            } catch (IOException e) {
                System.out.println("Error creating dealership file: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Invalid response. Please type 'yes' or 'no'.");
            return false;
        }
    }
    public static boolean authenticateUser() {
        System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "                                 🔐 LOGIN REQUIRED                                    " +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        String correctUsername = "vsm";
        String correctPassword = "vsm123";

        int attempts = 0;

        while (attempts < 3) {
            String enteredUsername = askQuestion("Username: ");
            String enteredPassword = askQuestion("Password: ");


            if (enteredUsername.equalsIgnoreCase(correctUsername) && enteredPassword.equals(correctPassword)) {
                System.out.println("Login successful.");
                System.out.println("-----------------------------------------------------------------------------------\n");
                return true;
            } else {
                    System.out.println("Input is correct, Try Again!.\n");
                attempts++;
            }
        }

        System.out.println("Too many failed attempts. Returning to verification.");
        return false;
    }

    private static void loadDealership() {
        DealershipFileManager dfm = new DealershipFileManager();
        newDealer = dfm.getDealership(fileName);
    }

    public static void processGetByPriceRequest() {
        boolean searching = true;
        while (searching) {
            System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "                            💰 SEARCH BY PRICE RANGE                                  " +
                    "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            double min = Double.parseDouble(getValidatedNumber("Enter minimum price: "));
            double max = Double.parseDouble(getValidatedNumber("Enter maximum price: "));
            ArrayList<Vehicle> results = newDealer.getVehiclesByPrice(min, max);
            displayVehicles(results);
            String choice = getValidString("Would you like to look for another vehicle by price (Y/N): ");
            if (!choice.equalsIgnoreCase("y")) {
                searching = false;
            }
        }
        System.out.println("-----------------------------------------------------------------------------------\n");
    }

    public static void processGetByMakeModelRequest() {
        boolean searching = true;
        while (searching) {
            System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "                        ⚙️ SEARCH BY MAKE AND MODEL                                 " +
                    "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            String make = capitalizeWords(getValidString("Enter make: "));
            String model = capitalizeWords(getValidString("Enter model: "));
            ArrayList<Vehicle> results = newDealer.getVehiclesByMakeModel(make, model);
            displayVehicles(results);
            String choice = getValidString("Would you like to look for another vehicle by make and model (Y/N): ");
            if (!choice.equalsIgnoreCase("y")) {
                searching = false;
            }
        }
        System.out.println("-----------------------------------------------------------------------------------\n");
    }

    public static void processGetByYearRequest() {
        boolean searching = true;
        while (searching) {
            System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "                           📅 SEARCH BY YEAR RANGE                                   " +
                    "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            int min = Integer.parseInt(getValidatedNumber("Enter minimum year: "));
            int max = Integer.parseInt(getValidatedNumber("Enter maximum year: "));
            ArrayList<Vehicle> results = newDealer.getVehiclesByYear(min, max);
            displayVehicles(results);
            String choice = getValidString("Would you like to look for another vehicle by year (Y/N): ");
            if (!choice.equalsIgnoreCase("y")) {
                searching = false;
            }
        }
        System.out.println("-----------------------------------------------------------------------------------\n");
    }

    public static void processGetByColorRequest() {
        boolean searching = true;
        while (searching) {
            System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "                             🎨 SEARCH BY COLOR                                     " +
                    "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            String color = capitalizeWords(getValidString("Enter color: "));
            ArrayList<Vehicle> results = newDealer.getVehiclesByColor(color);
            displayVehicles(results);
            String choice = getValidString("Would you like to look for another vehicle by color (Y/N): ");
            if (!choice.equalsIgnoreCase("y")) {
                searching = false;
            }
        }
        System.out.println("-----------------------------------------------------------------------------------\n");
    }

    public static void processGetByMileageRequest() {
        boolean searching = true;
        while (searching) {
            System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "                            🛣️ SEARCH BY MILEAGE RANGE                               " +
                    "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            int min = Integer.parseInt(getValidatedNumber("Enter minimum mileage: "));
            int max = Integer.parseInt(getValidatedNumber("Enter maximum mileage: "));
            ArrayList<Vehicle> results = newDealer.getVehiclesByMileage(min, max);
            displayVehicles(results);
            String choice = getValidString("Would you like to look for another vehicle by mileage (Y/N): ");
            if (!choice.equalsIgnoreCase("y")) {
                searching = false;
            }
        }
        System.out.println("-----------------------------------------------------------------------------------\n");
    }

    public static void processGetByVehicleTypeRequest() {
        boolean searching = true;
        while (searching) {
            System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "                            🚗 SEARCH BY VEHICLE TYPE                               " +
                    "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            String type = capitalizeWords(getValidString("Enter vehicle type: "));
            ArrayList<Vehicle> results = newDealer.getVehiclesByType(type);
            displayVehicles(results);
            String choice = getValidString("Would you like to look for another vehicle by type (Y/N): ");
            if (!choice.equalsIgnoreCase("y")) {
                searching = false;
            }
        }
        System.out.println("-----------------------------------------------------------------------------------\n");
    }


    public static void processAllVehiclesRequest() {
        System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "                               📋 ALL VEHICLES LIST                                 " +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        ArrayList<Vehicle> vehicles = newDealer.getAllVehicles();
        displayVehicles(vehicles);
        System.out.println("-----------------------------------------------------------------------------------\n");
    }

    public static void processAddVehicleRequest() {
        boolean adding = true;
        while (adding) {
            System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "                              ➕ ADD A NEW VEHICLE                                   " +
                    "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            int vin = Integer.parseInt(getValidatedNumber("Enter VIN: "));
            int year = Integer.parseInt(getValidatedNumber("Enter year: "));
            String make = capitalizeWords(getValidString("Enter make: "));
            String model = capitalizeWords(getValidString("Enter model: "));
            String type = capitalizeWords(getValidString("Enter type: "));
            String color = capitalizeWords(getValidString("Enter color: "));
            int mileage = Integer.parseInt(getValidatedNumber("Enter mileage: "));
            double price = Double.parseDouble(getValidatedNumber("Enter price: "));

            Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
            newDealer.addVehicle(newVehicle);

            DealershipFileManager dfm = new DealershipFileManager();
            dfm.saveDealership(newDealer, fileName);
            timer(700);
            System.out.println("Vehicle added successfully.");
            System.out.println("-----------------------------------------------------------------------------------\n");

            String again = getValidString("Would you like to add another vehicle? (Y/N): ");
            if (!again.equalsIgnoreCase("y")) {
                adding = false;
            }
        }
    }

    public static void processRemoveVehicleRequest() {
        boolean removing = true;
        while (removing) {
            System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "                             ❌ REMOVE A VEHICLE                                     " +
                    "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            int vin = Integer.parseInt(getValidatedNumber("Enter VIN of vehicle to remove: "));

            Vehicle vehicleToRemove = null;
            for (Vehicle currentVehicle : newDealer.getAllVehicles()) {
                if (currentVehicle.getVin() == vin) {
                    vehicleToRemove = currentVehicle;
                    break;
                }
            }
            timer(700);
            if (vehicleToRemove != null) {
                newDealer.removeVehicle(vehicleToRemove);
                DealershipFileManager dfm = new DealershipFileManager();
                dfm.saveDealership(newDealer , fileName);
                System.out.println("Vehicle removed successfully.");
            } else {
                System.out.println("Vehicle with that VIN not found.");
            }
            System.out.println("-----------------------------------------------------------------------------------\n");

            String again = getValidString("Would you like to remove another vehicle? (Y/N): ");
            if (!again.equalsIgnoreCase("y")) {
                removing = false;
            }
        }
    }
    public static void processSellOrLeaseVehicleRequest() {
        System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "                       📑 SELL OR LEASE A VEHICLE                              " +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        ArrayList<Vehicle> inventory = newDealer.getAllVehicles();
        displayVehicles(inventory);

        int vin = Integer.parseInt(getValidatedNumber("Enter VIN of the vehicle you want to sell or lease: "));

        Vehicle vehicleToProcess = null;
        for (Vehicle v : inventory) {
            if (v.getVin() == vin) {
                vehicleToProcess = v;
                break;
            }
        }

        if (vehicleToProcess == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.println("\nPlease enter customer information:");
        String name = capitalizeWords(getValidString("Name: "));
        String email = getValidString("Email: ");

        String contractDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        String contractType = getValidString("Is this a SALE or LEASE? (Type SALE/LEASE): ").toUpperCase();
        Contract contract = null;

        if (contractType.equals("SALE")) {
            String financeAnswer = getValidString("Do they want to finance the vehicle? (yes/no): ");
            boolean finance = financeAnswer.equalsIgnoreCase("yes");
            contract = new SalesContract(contractDate, name, email, vehicleToProcess, finance);
//    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean whetherToFinance, int processingFee) {
        } else if (contractType.equals("LEASE")) {
            int currentYear = LocalDate.now().getYear();
            if (vehicleToProcess.getYear() < currentYear - 3) {
                System.out.println("\nCannot lease vehicles over 3 years old.");
                return;
            }
            contract = new LeaseContract(contractDate, name, email, vehicleToProcess);
        } else {
            System.out.println("Invalid contract type.");
            return;
        }

        // Save contract and remove vehicle
        new ContractFileManager().saveContract(contract, getDealerFileBaseName());
        newDealer.removeVehicle(vehicleToProcess);
        new DealershipFileManager().saveDealership(newDealer, fileName);

        System.out.println("\nContract completed and vehicle removed from inventory.");
    }


    private static void displayVehicles(ArrayList<Vehicle> vehicleList) {
        timer(700);
        if (vehicleList.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for (Vehicle currentVehicle : vehicleList) {
                System.out.println(currentVehicle);
            }
        }
    }

    public static String askQuestion(String question) {
        System.out.print(question);
        return scanner.nextLine().trim();
    }

    private static String getValidString(String prompt) {
        while (true) {
            String input = askQuestion(prompt);
            if (input.isEmpty()) {
                System.out.println("This field cannot be empty.");
            } else if (input.matches("\\d+")) {
                System.out.println("Input cannot be numbers.");
            } else {
                return input;
            }
        }
    }

    private static String getValidatedNumber(String prompt) {
        while (true) {
            String input = askQuestion(prompt);
            if (input.isEmpty()) {
                System.out.println("This field cannot be empty.");
            } else if (!input.matches("[\\d.]+")) {
                System.out.println("Please enter a valid number (digits only).\n");
            } else {
                return input;
            }
        }
    }

    public static String capitalizeWords(String input) {
        String[] words = input.trim().toLowerCase().split("\\s+");
        StringBuilder capitalized = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                capitalized.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }
        return capitalized.toString().trim();
    }
    public static void timer (int timer){
        try {
            System.out.println("Loading.......");
            Thread.sleep(timer);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }


    }
    public static String getDealerFileBaseName() {
        return fileName.replace(".csv", "");
    }
}
