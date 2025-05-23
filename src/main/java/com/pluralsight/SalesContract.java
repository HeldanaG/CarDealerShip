package com.pluralsight;

public class SalesContract extends Contract{

    private double salesTax=0.05;
    private int recordingFee=100;
    private int processingFee;
    private boolean whetherToFinance;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean whetherToFinance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.whetherToFinance = whetherToFinance;
        this.processingFee = vehicleSold.getPrice() < 10000 ? 295 :495;
    }

    @Override
    public double getTotalPrice() {
        double tax = vehicleSold.getPrice() * salesTax;
        return vehicleSold.getPrice() + tax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment(){
        if (!whetherToFinance) return 0.0;
        double total = getTotalPrice();
        double rate = vehicleSold.getPrice() >= 10000 ? 0.0425 : 0.0525;
        int months = vehicleSold.getPrice() >= 10000 ? 48 : 24;
        return (total * rate / 12) / (1 - Math.pow(1 + rate / 12, -months));
    }
}
