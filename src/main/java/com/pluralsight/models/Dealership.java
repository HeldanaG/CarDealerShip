package com.pluralsight.models;

import java.util.ArrayList;

// Represents a car dealership and provides methods to manage its inventory of vehicles.
public class Dealership {
    private String dealerName;
    private String dealerAddress;
    private String dealerPhone;


    public Dealership(String dealerName, String dealerPhone, String dealerAddress) {
        this.dealerName = dealerName;
        this.dealerPhone = dealerPhone;
        this.dealerAddress = dealerAddress;

    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerAddress() {
        return dealerAddress;
    }

    public void setDealerAddress(String dealerAddress) {
        this.dealerAddress = dealerAddress;
    }

    public String getDealerPhone() {
        return dealerPhone;
    }

    public void setDealerPhone(String dealerPhone) {
        this.dealerPhone = dealerPhone;
    }
}