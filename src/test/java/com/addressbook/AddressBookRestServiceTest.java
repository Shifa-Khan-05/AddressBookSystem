package com.addressbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AddressBookRestServiceTest {

    AddressBookRestService service = new AddressBookRestService();

    @Test
    void givenServer_WhenContactsRequested_ShouldReturnResponse() {

        assertDoesNotThrow(() -> service.readContacts());
    }
}