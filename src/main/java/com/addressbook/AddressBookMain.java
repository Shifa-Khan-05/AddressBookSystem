package com.addressbook;

import java.util.Scanner;

public class AddressBookMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AddressBookSystem system = new AddressBookSystem();

        while (true) {

            System.out.println("\n1 Add AddressBook");
            System.out.println("2 Display AddressBooks");
            System.out.println("3 Use AddressBook");
            System.out.println("4 Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.println("Enter AddressBook Name:");
                    String name = sc.nextLine();

                    AddressBook addressBook = new AddressBook();

                    system.addAddressBook(name, addressBook);

                    break;

                case 2:

                    system.displayAddressBooks();

                    break;

                case 3:

                    System.out.println("Enter AddressBook Name to use:");
                    String bookName = sc.nextLine();

                    AddressBook book = system.getAddressBook(bookName);

                    if (book == null) {
                        System.out.println("AddressBook not found");
                        break;
                    }

                    manageContacts(book, sc);

                    break;

                case 4:

                    System.out.println("Exiting...");
                    return;

                default:

                    System.out.println("Invalid choice");
            }
        }
    }

    public static void manageContacts(AddressBook book, Scanner sc) {

        while (true) {

            System.out.println("\n1 Add Contact");
            System.out.println("2 Display Contacts");
            System.out.println("3 Edit Contact");
            System.out.println("4 Delete Contact");
            System.out.println("5 Search by City");
            System.out.println("6 Search by State");
            System.out.println("7 View Persons by City");
            System.out.println("8 View Persons by State");
            System.out.println("9 Count by City ");
            System.out.println("10 Count by State");
            System.out.println("11 Back");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

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

                    Contact contact = new Contact(firstName, lastName, address, city, state, zip, phone, email);

                    book.addContact(contact);

                    break;

                case 2:

                    book.displayContacts();
                    break;

                case 3:

                    System.out.println("Enter name to edit:");
                    String editName = sc.nextLine();
                    book.editContact(editName);

                    break;

                case 4:

                    System.out.println("Enter name to delete:");
                    String deleteName = sc.nextLine();
                    book.deleteContact(deleteName);

                    break;

                case 5:

                    System.out.println("Enter city:");
                    String searchCity = sc.nextLine();
                    book.searchByCity(searchCity);

                    break;

                case 6:

                    System.out.println("Enter state:");
                    String searchState = sc.nextLine();
                    book.searchByState(searchState);

                    break;
                case 7:

                    System.out.println("Enter city:");
                    String cityy = sc.nextLine();
                    book.viewPersonsByCity(cityy);

                    break;

                case 8:

                    System.out.println("Enter state:");
                    String stat = sc.nextLine();
                    book.viewPersonsByState(stat);

                    break;

                case 9:

                    System.out.println("Enter city:");
                    String cityCount = sc.nextLine();

                    book.countByCity(cityCount);

                    break;
                    
                case 10:

                    System.out.println("Enter state:");
                    String stateCount = sc.nextLine();

                    book.countByState(stateCount);

                    break;
                    
                case 11:
                    return;
                    
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}