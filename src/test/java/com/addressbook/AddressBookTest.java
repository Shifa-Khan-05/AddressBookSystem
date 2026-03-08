package com.addressbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddressBookTest {

    AddressBook addressBook;

    @BeforeEach
    void setup() {
        addressBook = new AddressBook();
    }

    @Test
    void givenContact_WhenAdded_ShouldReturnTrue() {

        Contact contact = new Contact(
                "Shifa",
                "Khan",
                "Bhopal",
                "Bhopal",
                "MP",
                "462001",
                "9876543210",
                "shifa@gmail.com"
        );

        addressBook.addContact(contact);

        assertEquals(1, addressBook.contactList.size());
    }

    @Test
    void givenDuplicateContact_WhenAdded_ShouldNotBeAdded() {

        Contact contact = new Contact(
                "Shifa",
                "Khan",
                "Bhopal",
                "Bhopal",
                "MP",
                "462001",
                "9876543210",
                "shifa@gmail.com"
        );

        addressBook.addContact(contact);
        addressBook.addContact(contact);

        assertEquals(1, addressBook.contactList.size());
    }

    @Test
    void givenContact_WhenEdited_ShouldUpdateDetails() {

        Contact contact = new Contact(
                "Shifa",
                "Khan",
                "Bhopal",
                "Bhopal",
                "MP",
                "462001",
                "9876543210",
                "shifa@gmail.com"
        );

        addressBook.addContact(contact);

        contact.city = "Indore";

        assertEquals("Indore", contact.city);
    }

    @Test
    void givenContact_WhenDeleted_ShouldRemoveContact() {

        Contact contact = new Contact(
                "Shifa",
                "Khan",
                "Bhopal",
                "Bhopal",
                "MP",
                "462001",
                "9876543210",
                "shifa@gmail.com"
        );

        addressBook.addContact(contact);

        addressBook.deleteContact("Shifa");

        assertEquals(0, addressBook.contactList.size());
    }

    @Test
    void givenContacts_WhenSorted_ShouldReturnSortedList() {

        Contact c1 = new Contact(
                "Rahul","Sharma","Delhi","Delhi","Delhi","110001","9876540000","rahul@gmail.com");

        Contact c2 = new Contact(
                "Aman","Verma","Indore","Indore","MP","452001","9876541111","aman@gmail.com");

        addressBook.addContact(c1);
        addressBook.addContact(c2);

        addressBook.sortContactsByName();

        assertEquals(2, addressBook.contactList.size());
    }

    @Test
    void givenContacts_WhenCountByCity_ShouldReturnCorrectCount() {

        Contact c1 = new Contact(
                "Shifa","Khan","Bhopal","Bhopal","MP","462001","9876543210","shifa@gmail.com");

        Contact c2 = new Contact(
                "Rahul","Sharma","Delhi","Delhi","Delhi","110001","9876540000","rahul@gmail.com");

        addressBook.addContact(c1);
        addressBook.addContact(c2);

        long count = addressBook.contactList.stream()
                .filter(c -> c.city.equalsIgnoreCase("Bhopal"))
                .count();

        assertEquals(1, count);
    }
}