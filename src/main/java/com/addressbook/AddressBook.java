package com.addressbook;

import java.util.Scanner;

public class AddressBook {

    Contact contact;

    public void addContact(Contact contact) {
        this.contact = contact;
    }

    public void displayContact() {
        contact.displayContact();
    }

    public void editContact(String name) {

        Scanner sc = new Scanner(System.in);

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

            System.out.println("Contact Updated Successfully");

        } else {
            System.out.println("Contact not found");
        }
    }
}