package com.addressbook;

public class AddressBook {

    Contact contact;

    public void addContact(Contact contact) {
        this.contact = contact;
    }

    public void displayContact() {
        contact.displayContact();
    }
}