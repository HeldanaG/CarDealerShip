package com.pluralsight;

import java.io.*;
import java.util.*;

public class DealershipFileManager {

    public Dealership getDealership(String fileName) {

        Dealership newDealer = null;

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/" + fileName));

            String newDealerInfo = fileReader.readLine();

            if (newDealerInfo != null) {
                while (newDealerInfo != null && (newDealerInfo.startsWith("-") || newDealerInfo.trim().isEmpty() || newDealerInfo.contains("Name | Address | Phone"))) {
                    newDealerInfo = fileReader.readLine();
                }

                String[] dealershipParts = newDealerInfo.split("\\|");
                String dealerName = dealershipParts[0].trim();
                String dealerAddress = dealershipParts[1].trim();
                String dealerPhone = dealershipParts[2].trim();

                newDealer = new Dealership(dealerName, dealerAddress, dealerPhone);
            }

            String newVehicleInfo;
            while ((newVehicleInfo = fileReader.readLine()) != null) {
                if (newVehicleInfo.startsWith("-") || newVehicleInfo.trim().isEmpty() || newVehicleInfo.contains("VIN")) {
                    continue;
                }
                String[] vehicleParts = newVehicleInfo.split("\\|");

                int vin = Integer.parseInt(vehicleParts[0].trim());
                int year = Integer.parseInt(vehicleParts[1].trim());
                String make = vehicleParts[2].trim();
                String model = vehicleParts[3].trim();
                String type = vehicleParts[4].trim();
                String color = vehicleParts[5].trim();
                int odometer = Integer.parseInt(vehicleParts[6].trim());
                double price = Double.parseDouble(vehicleParts[7].replace("$", "").trim());

                Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                newDealer.addVehicle(newVehicle);
            }

            fileReader.close();
        } catch (IOException e) {
            System.out.println("Error reading dealership file: src/main/resources/" + fileName + " (" + e.getMessage() + ")");
        }

        return newDealer;
    }

    public void saveDealership(Dealership newDealer, String fileName) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("src/main/resources/" + fileName));

            fileWriter.write("---- Dealership Information ----\n");
            fileWriter.write("Name | Address | Phone\n");
            fileWriter.write(newDealer.getDealerName() + "|" + newDealer.getDealerAddress() + "|" + newDealer.getDealerPhone());
            fileWriter.newLine();
            fileWriter.write("-----------------------------------------------------------------------------------\n\n");

            fileWriter.write("---- Vehicle Inventory ----\n");
            fileWriter.write(String.format("%-8s | %-6s | %-10s | %-12s | %-12s | %-10s | %-8s | %-11s\n",
                    "VIN", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price"));
            fileWriter.write("-----------------------------------------------------------------------------------\n");

            ArrayList<Vehicle> inventory = newDealer.getAllVehicles();
            Collections.reverse(inventory);

            for (Vehicle newVehicle : inventory) {
                String line = String.format("%-8d | %-6d | %-10s | %-12s | %-12s | %-10s | %-8d | $%-10.2f",
                        newVehicle.getVin(),
                        newVehicle.getYear(),
                        newVehicle.getMake().trim(),
                        newVehicle.getModel().trim(),
                        newVehicle.getVehicleType().trim(),
                        newVehicle.getColor().trim(),
                        newVehicle.getOdometer(),
                        newVehicle.getPrice()
                );
                fileWriter.write(line);
                fileWriter.newLine();
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing dealership file: " + e.getMessage());
        }
    }
}
