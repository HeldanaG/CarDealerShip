package com.pluralsight.dao;

import com.pluralsight.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {

    private final DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public void addLease(LeaseContract leaseContract) {
        String sql = "INSERT INTO lease_contracts (VIN, ContractDate, CustomerName, CustomerEmail, ExpectedEndingValue, LeaseFee, TotalPrice, MonthlyPayment, TermMonths) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, leaseContract.getVin());
            stmt.setDate(2, Date.valueOf(leaseContract.getContractDate()));
            stmt.setString(3, leaseContract.getCustomerName());
            stmt.setString(4, leaseContract.getCustomerEmail());
            stmt.setDouble(5, leaseContract.getExpectedEndingValue());
            stmt.setDouble(6, leaseContract.getLeaseFee());
            stmt.setDouble(7, leaseContract.getTotalPrice());
            stmt.setDouble(8, leaseContract.getMonthlyPayment());
            stmt.setInt(9, leaseContract.getTermMonths());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();        }
    }

}
