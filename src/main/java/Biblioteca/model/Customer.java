package Biblioteca.model;

import Biblioteca.model.value_objects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Customer {
    private final LibraryNumber libraryNumber;
    private final Password password;
    private final Person name;
    private final EmailAddress emailAddress;
    private final PhoneNumber phoneNumber;
    private final List<LibraryItem> checkedOutItems;

    public Customer(LibraryNumber libraryNumber, Password password, Person name, EmailAddress emailAddress, PhoneNumber phoneNumber) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.checkedOutItems = new ArrayList<>();
    }

    boolean isCredentialsCorrect(LibraryNumber libraryNumber, Password password) {
        return libraryNumber.equals(this.libraryNumber) && password.equals(this.password);
    }

    public List<String> getDetails() {
        return Arrays.asList(name.getName(), emailAddress.getAddress(), phoneNumber.getNumber());
    }

    void checkOut(LibraryItem item) {
        checkedOutItems.add(item);
    }

    boolean hasCheckedOut(LibraryItem item) {
        return checkedOutItems.contains(item);
    }

    LibraryItem returnItem(Title title, TypeOfLibraryItem type) {
        if (!hasCheckedOut(type.createLibraryItemForTitle(title))) {
            return null;
        }

        final LibraryItem item = checkedOutItems.get(checkedOutItems.indexOf(
                type.createLibraryItemForTitle(title)));

        checkedOutItems.remove(item);
        return item;
    }
}
