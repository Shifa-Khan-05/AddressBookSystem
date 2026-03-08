package com.addressbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddressBookDBServiceTest {

    AddressBookDBService service;

    @BeforeEach
    void setup() {
        service = new AddressBookDBService();
    }

    @Test
    void givenContact_WhenAddedToDB_ShouldReturnTrue() throws Exception {

        Contact contact = new Contact(
                "Shifa","Khan","Bhopal","Bhopal","MP","462001","9876543210","shifa@gmail.com"
        );

        service.addContactToDatabase(contact);

        Connection connection = DriverManager.getConnection(
                "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1","sa","");

        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(
                "SELECT * FROM contact WHERE first_name='Shifa'"
        );

        assertTrue(rs.next());
    }

    @Test
    void givenContact_WhenUpdated_ShouldChangeCity() throws Exception {

        service.updateContact("Shifa","Indore");

        Connection connection = DriverManager.getConnection(
                "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1","sa","");

        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(
                "SELECT city FROM contact WHERE first_name='Shifa'"
        );

        rs.next();

        assertEquals("Indore", rs.getString("city"));
    }

    @Test
    void givenContacts_WhenRetrieved_ShouldReturnResults() {

        assertDoesNotThrow(() -> service.readContacts());
    }

    @Test
    void givenDateRange_WhenQueried_ShouldReturnContacts() {

        assertDoesNotThrow(() ->
                service.readContactsByDateRange("2020-01-01","2030-01-01"));
    }

    @Test
    void givenContacts_WhenCountedByCity_ShouldWork() {

        assertDoesNotThrow(() -> service.countContactsByCity());
    }

    @Test
    void givenContacts_WhenCountedByState_ShouldWork() {

        assertDoesNotThrow(() -> service.countContactsByState());
    }

    @Test
    void givenMultipleContacts_WhenInsertedUsingThreads_ShouldWork() {

        assertDoesNotThrow(() -> service.addMultipleContacts(
                java.util.List.of(
                        new Contact("Aman","Verma","Indore","Indore","MP","452001","9999999999","aman@gmail.com"),
                        new Contact("Rahul","Sharma","Delhi","Delhi","Delhi","110001","8888888888","rahul@gmail.com")
                )
        ));
    }
}