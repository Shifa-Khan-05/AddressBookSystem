package com.addressbook;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AddressBookFileIOService {

    public void writeContacts(List<Contact> contacts) {

        try {
            FileWriter writer = new FileWriter("AddressBook.txt");

            for(Contact contact : contacts) {

                writer.write(contact.firstName + " "
                        + contact.lastName + " "
                        + contact.city + " "
                        + contact.state + "\n");
            }

            writer.close();

            System.out.println("Contacts saved to file");

        } catch(IOException e) {
            System.out.println("Error writing file");
        }
    }
}