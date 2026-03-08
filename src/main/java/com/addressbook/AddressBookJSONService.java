package com.addressbook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AddressBookJSONService {

    public void writeContactsToJSON(List<Contact> contacts) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("AddressBook.json")) {

            gson.toJson(contacts, writer);

            System.out.println("Contacts written to JSON file");

        } catch (IOException e) {
            System.out.println("Error writing JSON file");
        }
    }
}