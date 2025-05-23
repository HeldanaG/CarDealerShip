package com.pluralsight;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = vehicleSold.getPrice() * 0.5;
        this.leaseFee = vehicleSold.getPrice() * 0.07;
    }

    @Override
    public double getTotalPrice() {
        return vehicleSold.getPrice() + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double depreciation = (vehicleSold.getPrice() - expectedEndingValue) / 36.0;
        double interest = (vehicleSold.getPrice() + expectedEndingValue) * 0.04 / 2;
        return depreciation + interest / 36.0;
    }
}
