package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    public void saveContract(Contract contract, String dealerFileBaseName) {
        try {
            String dealershipFileName = dealerFileBaseName + "Contracts.csv";
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/" + dealershipFileName, true));
            StringBuilder sb = new StringBuilder();

            if (contract instanceof SalesContract sc) {
                sb.append("SALE|");
                sb.append(sc.getDate()).append("|")
                        .append(sc.getCustomerName()).append("|")
                        .append(sc.getCustomerEmail()).append("|")
                        .append(sc.getVehicleSold().getVin()).append("|")
                        .append(sc.getVehicleSold().getYear()).append("|")
                        .append(sc.getVehicleSold().getMake()).append("|")
                        .append(sc.getVehicleSold().getModel()).append("|")
                        .append(sc.getVehicleSold().getVehicleType()).append("|")
                        .append(sc.getVehicleSold().getColor()).append("|")
                        .append(sc.getVehicleSold().getOdometer()).append("|")
                        .append(String.format("%.2f", sc.getVehicleSold().getPrice())).append("|")
                        .append(String.format("%.2f", sc.getVehicleSold().getPrice() * 0.05)).append("|") // sales tax
                        .append("100.00|")
                        .append(sc.getVehicleSold().getPrice() < 10000 ? "295.00|" : "495.00|")
                        .append(String.format("%.2f", sc.getTotalPrice())).append("|")
                        .append(sc.getMonthlyPayment() > 0 ? "YES|" : "NO|")
                        .append(String.format("%.2f", sc.getMonthlyPayment()));
            }
            else if (contract instanceof LeaseContract lc) {
                sb.append("LEASE|");
                sb.append(lc.getDate()).append("|")
                        .append(lc.getCustomerName()).append("|")
                        .append(lc.getCustomerEmail()).append("|")
                        .append(lc.getVehicleSold().getVin()).append("|")
                        .append(lc.getVehicleSold().getYear()).append("|")
                        .append(lc.getVehicleSold().getMake()).append("|")
                        .append(lc.getVehicleSold().getModel()).append("|")
                        .append(lc.getVehicleSold().getVehicleType()).append("|")
                        .append(lc.getVehicleSold().getColor()).append("|")
                        .append(lc.getVehicleSold().getOdometer()).append("|")
                        .append(String.format("%.2f", lc.getVehicleSold().getPrice())).append("|")
                        .append(String.format("%.2f", lc.getVehicleSold().getPrice() * 0.5)).append("|") // expected end value
                        .append(String.format("%.2f", lc.getVehicleSold().getPrice() * 0.07)).append("|") // lease fee
                        .append(String.format("%.2f", lc.getTotalPrice())).append("|")
                        .append(String.format("%.2f", lc.getMonthlyPayment()));
            }

            writer.write(sb.toString());
            writer.newLine();
            writer.close();

            // Print receipt
            System.out.println("\n|==================== RECEIPT ====================|");
            System.out.println("| Date: " + contract.getDate());
            System.out.println("| Customer Name: " + contract.getCustomerName());
            System.out.println("| Customer Email: " + contract.getCustomerEmail());
            System.out.println("| -----------------------------------------------|");
            System.out.println("| Vehicle:");
            System.out.println("| " + contract.getVehicleSold());
            System.out.println("| Total Price with tax: $" + String.format("%.2f", contract.getTotalPrice()));
            System.out.println("| Monthly Payment with tax: $" + String.format("%.2f", contract.getMonthlyPayment()));
            System.out.println("|===============================================|\n");

        } catch (IOException e) {
            System.out.println("Error writing contract file: " + e.getMessage());
        }
    }
}
