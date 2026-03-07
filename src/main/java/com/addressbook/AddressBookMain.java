package com.addressbook;

import java.util.Scanner;

public class AddressBookMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AddressBook addressBook = new AddressBook();

        System.out.println("Welcome to Address Book Program");

        while(true) {

            System.out.println("\n1. Add Contact");
            System.out.println("2. Display Contacts");
            System.out.println("3. Edit Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {

                case 1:

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

                    addressBook.addContact(contact);

                    break;

                case 2:

                    addressBook.displayContacts();

                    break;

                case 3:

                    System.out.println("Enter name to edit:");
                    String editName = sc.nextLine();

                    addressBook.editContact(editName);

                    break;

                case 4:

                    System.out.println("Enter name to delete:");
                    String deleteName = sc.nextLine();

                    addressBook.deleteContact(deleteName);

                    break;

                case 5:

                    System.out.println("Exiting program...");
                    return;

                default:

                    System.out.println("Invalid choice");
            }
        }
    }
}