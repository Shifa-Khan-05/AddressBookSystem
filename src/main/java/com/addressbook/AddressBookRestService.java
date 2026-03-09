package com.addressbook;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.*;

public class AddressBookRestService {

    private static final String URL = "https://jsonplaceholder.typicode.com/users";

    public void readContacts() {

        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(URL, String.class);

        System.out.println(response);
    }
    public void addContactsAsync(List<Contact> contacts) {

        long startTime = System.currentTimeMillis();

        contacts.forEach(contact -> {

            Thread thread = new Thread(() -> {

                try {

                    RestTemplate restTemplate = new RestTemplate();

                    ResponseEntity<String> response =
                            restTemplate.postForEntity(URL, contact, String.class);

                    System.out.println("Thread: " + Thread.currentThread().getName());
                    System.out.println("Response: " + response.getBody());

                } catch (Exception e) {

                    System.out.println("Error calling server: " + e.getMessage());
                }

            });

            thread.start();
        });

        long endTime = System.currentTimeMillis();

        System.out.println("Main thread finished in: " + (endTime - startTime) + " ms");
    }
    
    
    
    

}