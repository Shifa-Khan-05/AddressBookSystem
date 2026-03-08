package com.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*;


public class AddressBookDBService {

    private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    // Create table once
    private void createTable(Connection connection) throws Exception {

        Statement statement = connection.createStatement();

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
                        "email VARCHAR(200)," +
                        "date_added DATE)"
        );
    }

    // UC20 – Add contact to DB
    public void addContactToDB(Contact contact) {

        try {

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            createTable(connection);

            String sql = "INSERT INTO contact(first_name,last_name,address,city,state,zip,phone,email,date_added) VALUES(?,?,?,?,?,?,?,?,CURRENT_DATE)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, contact.firstName);
            ps.setString(2, contact.lastName);
            ps.setString(3, contact.address);
            ps.setString(4, contact.city);
            ps.setString(5, contact.state);
            ps.setString(6, contact.zip);
            ps.setString(7, contact.phoneNumber);
            ps.setString(8, contact.email);

            ps.executeUpdate();

            connection.close();

            System.out.println("Contact inserted into database");

        } catch(Exception e) {

            System.out.println("Database error: " + e.getMessage());
        }
    }

    // UC16 – Read contacts
    public void readContacts() {

        try {

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            createTable(connection);

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT * FROM contact");

            while(result.next()) {

                System.out.println(
                        result.getString("first_name") + " "
                                + result.getString("last_name") + " "
                                + result.getString("city") + " "
                                + result.getString("state")
                );
            }

            connection.close();

        } catch(Exception e) {

            System.out.println("Database error: " + e.getMessage());
        }
    }

    // UC17 – Update contact
    public void updateContact(String firstName, String city) {

        try {

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            createTable(connection);

            String sql = "UPDATE contact SET city=? WHERE first_name=?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, city);
            ps.setString(2, firstName);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("Contact updated successfully");
            } else {
                System.out.println("Contact not found");
            }

            connection.close();

        } catch(Exception e) {

            System.out.println("Database error: " + e.getMessage());
        }
    }

    // UC18 – Retrieve by date range
    public void readContactsByDateRange(String start, String end) {

        try {

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            createTable(connection);

            String sql = "SELECT * FROM contact WHERE date_added BETWEEN ? AND ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, start);
            ps.setString(2, end);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while(rs.next()) {

                found = true;

                System.out.println(
                        rs.getString("first_name") + " "
                                + rs.getString("last_name") + " "
                                + rs.getString("city") + " "
                                + rs.getString("state") + " "
                                + rs.getDate("date_added")
                );
            }

            if(!found){
                System.out.println("No contacts found in this date range");
            }

            connection.close();

        } catch(Exception e) {

            System.out.println("Database error: " + e.getMessage());
        }
    }
    
    
    public void countContactsByCity() {

        try {

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            createTable(connection);

            String sql = "SELECT city, COUNT(*) AS total FROM contact GROUP BY city";

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                System.out.println(
                        rs.getString("city") + " : " + rs.getInt("total")
                );
            }

            connection.close();

        } catch(Exception e) {

            System.out.println("Database error: " + e.getMessage());
        }
    }

    public void countContactsByState() {

        try {

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            createTable(connection);

            String sql = "SELECT state, COUNT(*) AS total FROM contact GROUP BY state";

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                System.out.println(
                        rs.getString("state") + " : " + rs.getInt("total")
                );
            }

            connection.close();

        } catch(Exception e) {

            System.out.println("Database error: " + e.getMessage());
        }
    }
    
    
    
    public void addContactToDatabase(Contact contact) {

        try {

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            createTable(connection);

            String sql = "INSERT INTO contact(first_name,last_name,address,city,state,zip,phone,email,date_added) VALUES(?,?,?,?,?,?,?,?,CURRENT_DATE)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, contact.firstName);
            ps.setString(2, contact.lastName);
            ps.setString(3, contact.address);
            ps.setString(4, contact.city);
            ps.setString(5, contact.state);
            ps.setString(6, contact.zip);
            ps.setString(7, contact.phoneNumber);
            ps.setString(8, contact.email);

            ps.executeUpdate();

            connection.close();

            System.out.println("Contact added to database successfully");

        } catch(Exception e) {

            System.out.println("Database error: " + e.getMessage());
        }
    }
    
    
    
    public void addMultipleContacts(List<Contact> contacts) {

        long startTime = System.currentTimeMillis();

        contacts.forEach(contact -> {

            Thread thread = new Thread(() -> {

                addContactToDatabase(contact);

            });

            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        long endTime = System.currentTimeMillis();

        System.out.println("Time taken to add contacts: " + (endTime - startTime) + " ms");
    }


}
