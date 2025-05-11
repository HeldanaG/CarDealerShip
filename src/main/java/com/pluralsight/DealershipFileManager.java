package com.pluralsight;

import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class DealershipFileManager {

   public Dealership getDealership() {

       Dealership newDealer =null;

       try{
           BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/inventory.csv"));

           String newDealerInfo = fileReader.readLine();

           if (newDealerInfo != null) {
               // Split the line into parts: name, address, phone
               String[] dealershipParts = newDealerInfo.split("\\|");
               String dealerName = dealershipParts[0];
               String dealerAddress = dealershipParts[1];
               String dealerPhone = dealershipParts[2];

               // Create the dealership object
               newDealer = new Dealership(dealerName, dealerAddress, dealerPhone);
           }
           String newVehicleInfo;
           while ((newVehicleInfo = fileReader.readLine()) != null) {
               String[] vehicleParts = newVehicleInfo.split("\\|");

               // Parse vehicle fields
               int vin = Integer.parseInt(vehicleParts[0]);
               int year = Integer.parseInt(vehicleParts[1]);
               String make = vehicleParts[2];
               String model = vehicleParts[3];
               String type = vehicleParts[4];
               String color = vehicleParts[5];
               int odometer = Integer.parseInt(vehicleParts[6]);
               double price = Double.parseDouble(vehicleParts[7]);

               // Add each vehicle to the dealership
               Vehicle newVehicle = new Vehicle(vin, price, color, odometer,  type,make,model, year);
               newDealer.addVehicle(newVehicle);
           }

           fileReader.close();
       } catch (IOException e) {
           System.out.println("Error reading dealership file: " + e.getMessage());
       }

       return newDealer;
   }

    // Writes the dealership and its vehicles to the CSV file
    public void saveDealership(Dealership newDealer) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("src/main/resources/inventory.csv"));

            // Write dealership header info (first line)
            fileWriter.write(newDealer.getDealerName() + "|" + newDealer.getDealerAddress() + "|" + newDealer.getDealerPhone());
            fileWriter.newLine();

            ArrayList<Vehicle> inventory = newDealer.getAllVehicles();

            for (Vehicle newVehicle : inventory) {
                String line = newVehicle.getVin() + "|" +
                        newVehicle.getYear() + "|" +
                        newVehicle.getMake() + "|" +
                        newVehicle.getModel() + "|" +
                        newVehicle.getVehicleType() + "|" +
                        newVehicle.getColor() + "|" +
                        newVehicle.getOdometer() + "|" +
                        newVehicle.getPrice();
                fileWriter.write(line);
                fileWriter.newLine();
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing dealership file: " + e.getMessage());
        }
    }



}