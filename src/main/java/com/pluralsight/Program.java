package com.pluralsight;

import com.pluralsight.dao.LeaseDao;
import com.pluralsight.dao.SalesDao;
import com.pluralsight.dao.VehicleDao;
import org.apache.commons.dbcp2.BasicDataSource;

public class Program {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Usage: java com.pluralsight.App <username> <password>");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/cardealership");
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        VehicleDao vehicleDao = new VehicleDao(dataSource);
        SalesDao salesDao = new SalesDao(dataSource);
        LeaseDao leaseDao = new LeaseDao(dataSource);

        UserInterface userInterface = new UserInterface(vehicleDao, salesDao, leaseDao);
        userInterface.display();

    }
}
