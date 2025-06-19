package com.pluralsight.models;

public class Vehicle {
    private String vin;
    private int year;
    private String make;
    private String model;
    private String Type;
    private String color;
    private int odometer;
    private double price;

    public Vehicle() {
    }

    // This class represents one vehicle and its data in the dealership's inventory.
    public Vehicle(String vin, int year, String make, String model, String Type, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.Type = Type;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }


    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format(
                "%-17s | %-6d | %-10s | %-12s | %-12s | %-10s | %-8d | $%-10.2f",
                vin, year, make, model, Type, color, odometer, price
        );
    }


}
