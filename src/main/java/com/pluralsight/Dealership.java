package com.pluralsight;

import java.util.ArrayList;

// Represents a car dealership and provides methods to manage its inventory of vehicles.
public class Dealership {
    private String dealerName;
    private String dealerAddress;
    private String dealerPhone;

    private ArrayList<Vehicle> inventory;

    public Dealership(String dealerName, String dealerPhone, String dealerAddress) {
        this.dealerName = dealerName;
        this.dealerPhone = dealerPhone;
        this.dealerAddress = dealerAddress;
        this.inventory= new ArrayList<Vehicle>();

    }

    public ArrayList<Vehicle> getVehiclesByPrice(double minPrice,  double maxPrice){

        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle newVehicle : inventory) {
            if (newVehicle.getPrice() >= minPrice && newVehicle.getPrice() <= maxPrice) {
                results.add(newVehicle);
            }
        }
        return results;
    }
    public ArrayList<Vehicle> getVehiclesByMakeModel(String vehicleMake, String vehicleModel) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle newVehicle : inventory) {
            if (newVehicle.getMake().equalsIgnoreCase(vehicleMake) && newVehicle.getModel().equalsIgnoreCase(vehicleModel)) {
                results.add(newVehicle);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle newVehicle : inventory) {
            if (newVehicle.getYear() >= minYear && newVehicle.getYear() <= maxYear) {
                results.add(newVehicle);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle newVehicle : inventory) {
            if (newVehicle.getColor().equalsIgnoreCase(color)) {
                results.add(newVehicle);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int minMileage, int maxMileage) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle newVehicle : inventory) {
            if (newVehicle.getOdometer() >= minMileage && newVehicle.getOdometer() <= maxMileage) {
                results.add(newVehicle);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehiclesByType(String vehicleType) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle newVehicle : inventory) {
            if (newVehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                results.add(newVehicle);
            }
        }
        return results;
    }
    public void addVehicle(Vehicle newVehicle) {
        inventory.add(newVehicle);
    }

    public void removeVehicle(Vehicle newVehicle) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getVin() == newVehicle.getVin()) {
                inventory.remove(i);
                break;
            }
        }
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }

    public String getDealerName() {
        return dealerName;
    }
    public String getDealerAddress() {
        return dealerAddress;
    }
    public String getDealerPhone() {
        return dealerPhone;
    }
}