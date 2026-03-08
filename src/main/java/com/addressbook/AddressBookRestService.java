package com.addressbook;

import org.springframework.web.client.RestTemplate;

public class AddressBookRestService {

    private static final String URL = "https://jsonplaceholder.typicode.com/users";

    public void readContacts() {

        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(URL, String.class);

        System.out.println(response);
    }
}