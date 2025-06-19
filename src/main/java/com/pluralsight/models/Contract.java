package com.pluralsight.models;

import java.time.LocalDate;

public abstract class Contract {
    protected int contractId;
    protected Vehicle vehicleSold;
    protected LocalDate contractDate;
    protected String customerName;
    protected String customerEmail;

    public Contract(Vehicle vehicleSold, LocalDate contractDate, String customerName, String customerEmail) {
        this.vehicleSold = vehicleSold;
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
