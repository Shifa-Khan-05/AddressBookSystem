package com.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddressBookDBService {

    private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public void readContacts() {

        try {

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement statement = connection.createStatement();

            // Create table if it does not exist
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS contact (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY," +
                            "first_name VARCHAR(100)," +
                            "last_name VARCHAR(100)," +
                            "address VARCHAR(200)," +
                            "city VARCHAR(100)," +
                            "state VARCHAR(100)," +
                            "zip VARCHAR(20)," +
                            "phone VARCHAR(20)," +
                            "email VARCHAR(200))"
            );

            // Insert sample data (only for testing)
            statement.executeUpdate(
                    "INSERT INTO contact(first_name,last_name,address,city,state,zip,phone,email) " +
                            "VALUES('Shifa','Khan','Bhopal','Bhopal','MP','462001','9876543210','shifa@gmail.com')"
            );

            ResultSet result = statement.executeQuery("SELECT * FROM contact");

            while (result.next()) {

                System.out.println(
                        result.getString("first_name") + " "
                                + result.getString("last_name") + " "
                                + result.getString("city") + " "
                                + result.getString("state")
                );
            }

            connection.close();

        } catch (Exception e) {

            System.out.println("Database error: " + e.getMessage());
        }
    }
}