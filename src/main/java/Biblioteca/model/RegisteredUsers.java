package Biblioteca.model;

import Biblioteca.model.value_objects.LibraryNumber;
import Biblioteca.model.value_objects.Password;

import java.util.List;

class RegisteredUsers {
    private final List<Customer> customers;
    private Customer loggedInUser;

    RegisteredUsers(List<Customer> customers) {
        this.customers = customers;
        loggedInUser = null;
    }

    boolean login(LibraryNumber libraryNumber, Password password) {
        for (Customer customer : customers) {
            if (customer.isCredentialsCorrect(libraryNumber, password)) {
                loggedInUser = customer;
                return true;
            }
        }

        return false;
    }

    Customer getLoggedInUser() {
        return loggedInUser;
    }

    void logout() {
        loggedInUser = null;
    }
}
