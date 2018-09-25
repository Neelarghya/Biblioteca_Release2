package Biblioteca.model;

import Biblioteca.model.value_objects.LibraryNumber;
import Biblioteca.model.value_objects.Password;
import Biblioteca.model.value_objects.Title;

import java.util.ArrayList;
import java.util.List;

// A place where libraryItems are kept
public class Library {
    private final List<LibraryItem> libraryItems;
    private final RegisteredUsers registeredUsers;

    public Library(List<LibraryItem> libraryItems, List<Customer> customers) {
        this.libraryItems = libraryItems;
        this.registeredUsers = new RegisteredUsers(customers);
    }

    public List<String> getItemDetails(TypeOfLibraryItem type) {
        List<String> outputs = new ArrayList<>();
        addItemDetails(outputs, type);
        return outputs;
    }

    private void addItemDetails(List<String> outputs, TypeOfLibraryItem type) {
        for (LibraryItem item : this.libraryItems) {
            if (item.isOfType(type)) {
                outputs.addAll(item.getDetails());
            }
        }
    }

    public void checkOut(Title title, TypeOfLibraryItem type) {
        if (!contains(title, type)) {
            return;
        }

        final LibraryItem item = this.libraryItems.get(getItemIndex(this.libraryItems, title, type));
        this.libraryItems.remove(item);
        getCurrentUser().checkOut(item);
    }

    private int getItemIndex(List<LibraryItem> libraryItems, Title title, TypeOfLibraryItem type) {
        for (int index = 0; index < libraryItems.size(); index++) {
            if (libraryItems.get(index).getTitle().equals(title) && libraryItems.get(index).isOfType(type)) {
                return index;
            }
        }
        return -1;
    }

    public boolean contains(Title title, TypeOfLibraryItem type) {
        return libraryItems.contains(type.createLibraryItemForTitle(title));

    }

    public boolean hasCheckedOut(Title title, TypeOfLibraryItem type) {
        final LibraryItem item = type.createLibraryItemForTitle(title);
        return getCurrentUser().hasCheckedOut(item);
    }

    public void returnItem(Title title, TypeOfLibraryItem type) {
        if (!hasCheckedOut(title, type)) {
            return;
        }

        final LibraryItem item = getCurrentUser().returnItem(title, type);
        this.libraryItems.add(item);
    }

    public boolean login(LibraryNumber libraryNumber, Password password) {
        return registeredUsers.login(libraryNumber, password);
    }

    public void logout() {
        registeredUsers.logout();
    }

    public Customer getCurrentUser() {
        return registeredUsers.getLoggedInUser();
    }
}
