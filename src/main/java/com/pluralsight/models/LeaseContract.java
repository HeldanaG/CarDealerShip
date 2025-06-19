package com.pluralsight.models;

import java.time.LocalDate;

public class LeaseContract extends Contract {
    private double leaseFee;
    private double expectedEndingValue;

    public LeaseContract(Vehicle vehicleSold, LocalDate contractDate, String customerName, String customerEmail,
                         double leaseFee, double expectedEndingValue) {
        super(vehicleSold, contractDate, customerName, customerEmail);
        this.leaseFee = leaseFee;
        this.expectedEndingValue = expectedEndingValue;
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
    public int getTermMonths() { return 36;}

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }
}
