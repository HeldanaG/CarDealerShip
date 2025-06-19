package com.pluralsight.dao;

import com.pluralsight.models.SalesContract;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {

    private BasicDataSource dataSource;

    public SalesDao(BasicDataSource dataSaource){
        this.dataSource=dataSource;
    }

    public void addSales(SalesContract salesContract){
        String sql = "INSERT INTO SalesContracts (VIN, SaleDate, CustomerName, CustomerEmail, SalesTaxAmount, RecordingFee, ProcessingFee, VehiclePrice, Finance, TermMonths, MonthlyPayment) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, salesContract.getVin());
            stmt.setDate(2, Date.valueOf(salesContract.getSaleDate()));
            stmt.setString(3, salesContract.getCustomerName());
            stmt.setString(4, salesContract.getCustomerEmail());
            stmt.setDouble(5, salesContract.getSalesTaxAmount());
            stmt.setDouble(6, salesContract.getRecordingFee());
            stmt.setDouble(7, salesContract.getProcessingFee());
            stmt.setDouble(8, salesContract.getVehiclePrice());
            stmt.setBoolean(9, salesContract.isFinance());
            stmt.setInt(10, salesContract.getTermMonths());
            stmt.setDouble(11, salesContract.getMonthlyPayment());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
