package com.addressbook;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddressBook {

    ArrayList<Contact> contactList = new ArrayList<>();
    
    Map<String, List<Contact>> cityMap = new HashMap<>();
    Map<String, List<Contact>> stateMap = new HashMap<>();
    
    public void addContact(Contact contact) {

        boolean duplicate = contactList.stream()
                .anyMatch(c -> c.equals(contact));

        if (duplicate) {
            System.out.println("Duplicate contact not allowed");
            return;
        }

        contactList.add(contact);

        cityMap.computeIfAbsent(contact.city, k -> new ArrayList<>()).add(contact);
        stateMap.computeIfAbsent(contact.state, k -> new ArrayList<>()).add(contact);

        // Insert into database
        AddressBookDBService service = new AddressBookDBService();
        service.addContactToDB(contact);

        System.out.println("Contact added successfully");
    }
    
    public void displayContacts() {

        if(contactList.isEmpty()) {
            System.out.println("No contacts available");
            return;
        }

        for(Contact contact : contactList) {
            contact.displayContact();
            System.out.println("-------------------");
        }
    }

    public void editContact(String name) {

        Scanner sc = new Scanner(System.in);

        for(Contact contact : contactList) {

            if(contact.firstName.equals(name)) {

                System.out.println("Enter new Address:");
                contact.address = sc.nextLine();

                System.out.println("Enter new City:");
                contact.city = sc.nextLine();

                System.out.println("Enter new State:");
                contact.state = sc.nextLine();

                System.out.println("Enter new Zip:");
                contact.zip = sc.nextLine();

                System.out.println("Enter new Phone:");
                contact.phoneNumber = sc.nextLine();

                System.out.println("Enter new Email:");
                contact.email = sc.nextLine();

                System.out.println("Contact updated successfully");
                return;
            }
        }

        System.out.println("Contact not found");
    }

    public void deleteContact(String name) {

        Contact foundContact = null;

        for(Contact contact : contactList) {

            if(contact.firstName.equals(name)) {
                foundContact = contact;
                break;
            }
        }

        if(foundContact != null) {
            contactList.remove(foundContact);
            System.out.println("Contact deleted successfully");
        }
        else {
            System.out.println("Contact not found");
        }
    }
    
    public void searchByCity(String city) {

        contactList.stream()
                .filter(contact -> contact.city.equalsIgnoreCase(city))
                .forEach(Contact::displayContact);
    }

    public void searchByState(String state) {

        contactList.stream()
                .filter(contact -> contact.state.equalsIgnoreCase(state))
                .forEach(Contact::displayContact);
    }
    
    public void viewPersonsByCity(String city) {

        List<Contact> persons = cityMap.get(city);

        if (persons == null || persons.isEmpty()) {
            System.out.println("No persons found in this city");
            return;
        }

        persons.forEach(Contact::displayContact);
    }
    
    public void viewPersonsByState(String state) {

        List<Contact> persons = stateMap.get(state);

        if (persons == null || persons.isEmpty()) {
            System.out.println("No persons found in this state");
            return;
        }

        persons.forEach(Contact::displayContact);
    }
    
    public void countByCity(String city) {

        long count = contactList.stream()
                .filter(contact -> contact.city.equalsIgnoreCase(city))
                .count();

        System.out.println("Number of contacts in " + city + " : " + count);
    }
    
    public void countByState(String state) {

        long count = contactList.stream()
                .filter(contact -> contact.state.equalsIgnoreCase(state))
                .count();

        System.out.println("Number of contacts in " + state + " : " + count);
    }
    
    public void sortContactsByName() {

        contactList.stream()
                .sorted((c1, c2) -> c1.firstName.compareToIgnoreCase(c2.firstName))
                .forEach(Contact::displayContact);
    }
    
    
    public void sortByCity() {

        contactList.stream()
                .sorted((c1, c2) -> c1.city.compareToIgnoreCase(c2.city))
                .forEach(Contact::displayContact);
    }
    
    
    
    public void sortByState() {

        contactList.stream()
                .sorted((c1, c2) -> c1.state.compareToIgnoreCase(c2.state))
                .forEach(Contact::displayContact);
    }
    
    public void sortByZip() {

        contactList.stream()
                .sorted((c1, c2) -> c1.zip.compareToIgnoreCase(c2.zip))
                .forEach(Contact::displayContact);
    }
    
    public void writeContactsToFile() {

        AddressBookFileIOService service = new AddressBookFileIOService();

        service.writeContacts(contactList);
    }
    
    public void writeContactsToCSV() {

        AddressBookCSVService service = new AddressBookCSVService();

        service.writeContactsToCSV(contactList);
    }
    
    public void writeContactsToJSON() {
        AddressBookJSONService service = new AddressBookJSONService();
        service.writeContactsToJSON(contactList);
    }
    
    public void readContactsFromDB() {
    	AddressBookDBService service = new AddressBookDBService();
        service.readContacts();
    }
    
    public void updateContactInDB(String firstName, String city) {
        AddressBookDBService service = new AddressBookDBService();
        service.updateContact(firstName, city);
    }
   
    public void readContactsByDateRange(String start, String end) {

        AddressBookDBService service = new AddressBookDBService();

        service.readContactsByDateRange(start, end);
    }
    
    public void countContactsByCityDB() {

        AddressBookDBService service = new AddressBookDBService();

        service.countContactsByCity();
    }
    
    public void countContactsByStateDB() {

        AddressBookDBService service = new AddressBookDBService();

        service.countContactsByState();
    }
}