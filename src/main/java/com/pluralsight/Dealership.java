package com.pluralsight;

import java.util.*;

public class Dealership {
    private String name;
    private String address;
    private String phone;

    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.inventory= new ArrayList<Vehicle>();

    }

    public ArrayList<Vehicle> getVehiclesByPrice(double min,  double max){

        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                results.add(vehicle);
            }
        }
        return results;
    }
    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                results.add(vehicle);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= minYear && vehicle.getYear() <= maxYear) {
                results.add(vehicle);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                results.add(vehicle);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int minMileage, int maxMileage) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= minMileage && vehicle.getOdometer() <= maxMileage) {
                results.add(vehicle);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> getVehiclesByType(String type) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(type)) {
                results.add(vehicle);
            }
        }
        return results;
    }
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getVin() == vehicle.getVin()) {
                inventory.remove(i); // stop after removing first match
                break;
            }
        }
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }

    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
}