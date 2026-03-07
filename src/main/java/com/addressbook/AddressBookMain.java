package com.addressbook;

import java.util.Scanner;

public class AddressBookMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Address Book Program");

        System.out.println("Enter First Name:");
        String firstName = sc.nextLine();

        System.out.println("Enter Last Name:");
        String lastName = sc.nextLine();

        System.out.println("Enter Address:");
        String address = sc.nextLine();

        System.out.println("Enter City:");
        String city = sc.nextLine();

        System.out.println("Enter State:");
        String state = sc.nextLine();

        System.out.println("Enter Zip:");
        String zip = sc.nextLine();

        System.out.println("Enter Phone:");
        String phone = sc.nextLine();

        System.out.println("Enter Email:");
        String email = sc.nextLine();

        Contact contact = new Contact(firstName,lastName,address,city,state,zip,phone,email);

        AddressBook addressBook = new AddressBook();

        addressBook.addContact(contact);

        System.out.println("\nContact Details:");
        addressBook.displayContact();

        System.out.println("\nEnter the first name to delete contact:");
        String name = sc.nextLine();

        addressBook.deleteContact(name);

        System.out.println("\nRemaining Contact:");
        addressBook.displayContact();
    }
}