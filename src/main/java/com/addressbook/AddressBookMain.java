package com.addressbook;

public class AddressBookMain {

    public static void main(String[] args) {

        System.out.println("Welcome to Address Book Program");

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

        contact.displayContact();
    }
}