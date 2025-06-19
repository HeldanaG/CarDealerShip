package com.pluralsight.dao;

import com.pluralsight.models.Vehicle;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private final DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Vehicle> findByPriceRange(double min, double max) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, min);
            stmt.setDouble(2, max);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vehicle v = new Vehicle();
                v.setVin(rs.getString("VIN"));
                v.setYear(rs.getInt("year"));
                v.setMake(rs.getString("make"));
                v.setModel(rs.getString("model"));
                v.setType(rs.getString("type"));
                v.setColor(rs.getString("color"));
                v.setOdometer(rs.getInt("odometer"));
                v.setPrice(rs.getDouble("price"));
                vehicles.add(v);
            }
        }

        return vehicles;
    }

    public List<Vehicle> findByMakeModel(String make, String model) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE make = ? AND model = ?";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, make);
            stmt.setString(2, model);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vehicle v = new Vehicle();
                v.setVin(rs.getString("VIN"));
                v.setYear(rs.getInt("year"));
                v.setMake(rs.getString("make"));
                v.setModel(rs.getString("model"));
                v.setType(rs.getString("type"));
                v.setColor(rs.getString("color"));
                v.setOdometer(rs.getInt("odometer"));
                v.setPrice(rs.getDouble("price"));
                vehicles.add(v);
            }
        }

        return vehicles;
    }

    public List<Vehicle> findByYearRange(int min, int max) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, min);
            stmt.setInt(2, max);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vehicle v = new Vehicle();
                v.setVin(rs.getString("VIN"));
                v.setYear(rs.getInt("year"));
                v.setMake(rs.getString("make"));
                v.setModel(rs.getString("model"));
                v.setType(rs.getString("type"));
                v.setColor(rs.getString("color"));
                v.setOdometer(rs.getInt("odometer"));
                v.setPrice(rs.getDouble("price"));
                vehicles.add(v);
            }
        }

        return vehicles;
    }

    public List<Vehicle> findByColor(String color) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE color = ?";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, color);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vehicle v = new Vehicle();
                v.setVin(rs.getString("VIN"));
                v.setYear(rs.getInt("year"));
                v.setMake(rs.getString("make"));
                v.setModel(rs.getString("model"));
                v.setType(rs.getString("type"));
                v.setColor(rs.getString("color"));
                v.setOdometer(rs.getInt("odometer"));
                v.setPrice(rs.getDouble("price"));
                vehicles.add(v);
            }
        }

        return vehicles;
    }

    public List<Vehicle> findByMileageRange(int min, int max) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, min);
            stmt.setInt(2, max);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vehicle v = new Vehicle();
                v.setVin(rs.getString("VIN"));
                v.setYear(rs.getInt("year"));
                v.setMake(rs.getString("make"));
                v.setModel(rs.getString("model"));
                v.setType(rs.getString("type"));
                v.setColor(rs.getString("color"));
                v.setOdometer(rs.getInt("odometer"));
                v.setPrice(rs.getDouble("price"));
                vehicles.add(v);
            }
        }

        return vehicles;
    }

    public List<Vehicle> findByType(String type) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE type = ?";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vehicle v = new Vehicle();
                v.setVin(rs.getString("VIN"));
                v.setYear(rs.getInt("year"));
                v.setMake(rs.getString("make"));
                v.setModel(rs.getString("model"));
                v.setType(rs.getString("type"));
                v.setColor(rs.getString("color"));
                v.setOdometer(rs.getInt("odometer"));
                v.setPrice(rs.getDouble("price"));
                vehicles.add(v);
            }
        }

        return vehicles;
    }

    public Vehicle findByVin(String vin) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE VIN = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vin);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Vehicle v = new Vehicle();
                v.setVin(rs.getString("VIN"));
                v.setYear(rs.getInt("year"));
                v.setMake(rs.getString("make"));
                v.setModel(rs.getString("model"));
                v.setType(rs.getString("type"));
                v.setColor(rs.getString("color"));
                v.setOdometer(rs.getInt("odometer"));
                v.setPrice(rs.getDouble("price"));
                return v;
            } else {
                return null;
            }
        }
    }


    public void addVehicle(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO vehicles (VIN, year, make, model, type, color, odometer, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getVin());
            stmt.setInt(2, vehicle.getYear());
            stmt.setString(3, vehicle.getMake());
            stmt.setString(4, vehicle.getModel());
            stmt.setString(5, vehicle.getType());
            stmt.setString(6, vehicle.getColor());
            stmt.setInt(7, vehicle.getOdometer());
            stmt.setDouble(8, vehicle.getPrice());

            stmt.executeUpdate();
        }
    }

    public boolean deleteVehicle(String vin) throws SQLException {
        String sql = "DELETE FROM vehicles WHERE VIN = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vin);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
