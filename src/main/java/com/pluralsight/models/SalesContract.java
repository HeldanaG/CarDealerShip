package com.pluralsight.models;

import java.time.LocalDate;

public class SalesContract extends Contract {
    private final double salesTax = 0.05;
    private double recordingFee;
    private double processingFee;
    private boolean whetherToFinance;

    public SalesContract(Vehicle vehicleSold, LocalDate contractDate, String customerName, String customerEmail,
                         double recordingFee, double processingFee, boolean whetherToFinance) {
        super(vehicleSold, contractDate, customerName, customerEmail);
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.whetherToFinance = whetherToFinance;
    }

    @Override
    public double getTotalPrice() {
        double tax = vehicleSold.getPrice() * salesTax;
        return vehicleSold.getPrice() + tax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!whetherToFinance) return 0.0;

        double total = getTotalPrice();
        double rate = vehicleSold.getPrice() >= 10000 ? 0.0425 : 0.0525;
        int months = vehicleSold.getPrice() >= 10000 ? 48 : 24;

        return (total * rate / 12) / (1 - Math.pow(1 + rate / 12, -months));
    }

    public double getSalesTax() {
        return salesTax;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isWhetherToFinance() {
        return whetherToFinance;
    }

    public void setWhetherToFinance(boolean whetherToFinance) {
        this.whetherToFinance = whetherToFinance;
    }
}
