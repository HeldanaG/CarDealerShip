package com.pluralsight;

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
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        // Launch the user interface and start the menu
        UserInterface.display();
    }

}