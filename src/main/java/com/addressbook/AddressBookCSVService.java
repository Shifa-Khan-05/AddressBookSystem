package com.addressbook;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AddressBookCSVService {

    public void writeContactsToCSV(List<Contact> contacts) {

        try {
            CSVWriter writer = new CSVWriter(new FileWriter("AddressBook.csv"));

            for (Contact contact : contacts) {

                String[] data = {
                        contact.firstName,
                        contact.lastName,
                        contact.address,
                        contact.city,
                        contact.state,
                        contact.zip,
                        contact.phoneNumber,
                        contact.email
                };

                writer.writeNext(data);
            }

            writer.close();

            System.out.println("Contacts written to CSV file");

        } catch (IOException e) {
            System.out.println("Error writing CSV file");
        }
    }
}