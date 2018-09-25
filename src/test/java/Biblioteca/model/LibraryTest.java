package Biblioteca.model;

import Biblioteca.model.value_objects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    private Library library;
    private Book book1, book2, libraryItemsNotInLibrary;
    List<LibraryItem> libraryItems;
    private List<Customer> customers;
    private Customer customer1, customer2;
    LibraryNumber libraryNumber1, libraryNumber2;
    Password password1, password2;

    @BeforeEach
    void init() {
        book1 = new Book(new Title("Book1"), new Person("Mrinal"), new Year(1996));
        book2 = new Book(new Title("Book2"), new Person("Arpan"), new Year(1997));
        libraryItemsNotInLibrary = new Book(new Title("Book not in library"),
                new Person("Nikhil"), new Year(1897));
        libraryItems = new ArrayList<>();
        libraryItems.add(book1);
        libraryItems.add(book2);

        customers = new ArrayList<>();
        libraryNumber1 = new LibraryNumber("number1");
        password1 = new Password("password1");
        customer1 = new Customer(libraryNumber1, password1, new Person("name"), new EmailAddress("default@gmail.com"), new PhoneNumber(1234567890));

        libraryNumber2 = new LibraryNumber("number2");
        password2 = new Password("password2");
        customer2 = new Customer(libraryNumber2, password2, new Person("name"), new EmailAddress("default@gmail.com"), new PhoneNumber(1234567890));

        customers.add(customer1);
        customers.add(customer2);

        library = new Library(libraryItems, customers);

        library.login(libraryNumber1, password1);
    }

    @DisplayName("Should print all the books")
    @Test
    void testDisplayingBookList() {
        assertEquals(Arrays.asList("Book1", "Mrinal", "1996", "Book2", "Arpan", "1997"),
                library.getItemDetails(TypeOfLibraryItem.BOOK));
    }

    @DisplayName("Should remove the book1 on check out")
    @Test
    void testCheckOutBook1() {
        library.checkOut(new Title("Book1"), TypeOfLibraryItem.BOOK);
        assertEquals(Arrays.asList("Book2", "Arpan", "1997"), library.getItemDetails(TypeOfLibraryItem.BOOK));
    }

    @DisplayName("Should remove the book2 on check out")
    @Test
    void testCheckOutBook2() {
        library.checkOut(new Title("Book2"), TypeOfLibraryItem.BOOK);
        assertEquals(Arrays.asList("Book1", "Mrinal", "1996"), library.getItemDetails(TypeOfLibraryItem.BOOK));
    }

    @DisplayName("Should contain book1 in Library")
    @Test
    void testContainsBook1() {
        assertTrue(library.contains(new Title("Book1"), TypeOfLibraryItem.BOOK));
    }

    @DisplayName("Should contain book2 in Library")
    @Test
    void testContainsBook2() {
        assertTrue(library.contains(new Title("Book2"), TypeOfLibraryItem.BOOK));
    }

    @DisplayName("Should not contain book not in library")
    @Test
    void testNotContainsBook() {
        assertFalse(library.contains(new Title("Book not in library"), TypeOfLibraryItem.BOOK));
    }

    @DisplayName("Should have checked book1 in after its checked")
    @Test
    void testHasCheckedBook1() {
        assertFalse(library.hasCheckedOut(new Title("Book1"), TypeOfLibraryItem.BOOK));
        library.checkOut(new Title("Book1"), TypeOfLibraryItem.BOOK);
        assertTrue(library.hasCheckedOut(new Title("Book1"), TypeOfLibraryItem.BOOK));
    }

    @DisplayName("Should have checked book2 in after its checked")
    @Test
    void testHasCheckedBook2() {
        assertFalse(library.hasCheckedOut(new Title("Book2"), TypeOfLibraryItem.BOOK));
        library.checkOut(new Title("Book2"), TypeOfLibraryItem.BOOK);
        assertTrue(library.hasCheckedOut(new Title("Book2"), TypeOfLibraryItem.BOOK));
    }

    @DisplayName("Should not have checked book not in library")
    @Test
    void testHasNotCheckedBook() {
        assertFalse(library.hasCheckedOut(new Title("Book not in library"), TypeOfLibraryItem.BOOK));
    }

    @DisplayName("Should be able to return book checked out")
    @Test
    void testReturnBook1() {
        assertEquals(Arrays.asList("Book1", "Mrinal", "1996", "Book2", "Arpan", "1997"),
                library.getItemDetails(TypeOfLibraryItem.BOOK));

        library.checkOut(new Title("Book1"), TypeOfLibraryItem.BOOK);
        assertEquals(Arrays.asList("Book2", "Arpan", "1997"), library.getItemDetails(TypeOfLibraryItem.BOOK));

        library.returnItem(new Title("Book1"), TypeOfLibraryItem.BOOK);
        assertEquals(Arrays.asList("Book2", "Arpan", "1997", "Book1", "Mrinal", "1996"),
                library.getItemDetails(TypeOfLibraryItem.BOOK));
    }

    @DisplayName("Should be able to return all books checked out")
    @Test
    void testReturnBook2() {
        assertEquals(Arrays.asList("Book1", "Mrinal", "1996", "Book2", "Arpan", "1997"),
                library.getItemDetails(TypeOfLibraryItem.BOOK));

        library.checkOut(new Title("Book1"), TypeOfLibraryItem.BOOK);
        library.checkOut(new Title("Book2"), TypeOfLibraryItem.BOOK);
        assertEquals(Arrays.asList(), library.getItemDetails(TypeOfLibraryItem.BOOK));

        library.returnItem(new Title("Book1"), TypeOfLibraryItem.BOOK);
        assertEquals(Arrays.asList("Book1", "Mrinal", "1996"),
                library.getItemDetails(TypeOfLibraryItem.BOOK));

        library.returnItem(new Title("Book2"), TypeOfLibraryItem.BOOK);
        assertEquals(Arrays.asList("Book1", "Mrinal", "1996", "Book2", "Arpan", "1997"),
                library.getItemDetails(TypeOfLibraryItem.BOOK));
    }

    @DisplayName("Should be unchanged on trying to return book not checked out")
    @Test
    void testNotReturnBookIfNotCheckedOut() {
        assertEquals(Arrays.asList("Book1", "Mrinal", "1996", "Book2", "Arpan", "1997"),
                library.getItemDetails(TypeOfLibraryItem.BOOK));

        library.returnItem(new Title("Book1"), TypeOfLibraryItem.BOOK);

        assertEquals(Arrays.asList("Book1", "Mrinal", "1996", "Book2", "Arpan", "1997"),
                library.getItemDetails(TypeOfLibraryItem.BOOK));
    }

    @DisplayName("Should get null if no one's logged in yet")
    @Test
    void testNoCurrentUser() {
        library = new Library(libraryItems, customers);
        assertNull(library.getCurrentUser());
    }

    @DisplayName("Should get customer1 if he logs in successfully logged in")
    @Test
    void testLoginCurrentUser() {
        library = new Library(libraryItems, customers);
        assertTrue(library.login(libraryNumber1, password1));
        assertEquals(customer1, library.getCurrentUser());
    }

    @DisplayName("Should be able to logout after logging in")
    @Test
    void testLogout() {
        library = new Library(libraryItems, customers);
        assertTrue(library.login(libraryNumber1, password1));
        assertEquals(customer1, library.getCurrentUser());
        library.logout();
        assertNull(library.getCurrentUser());
    }

    @DisplayName("Customer2 should not be able to return item taken by customer1")
    @Test
    void testNotReturnOthers(){
        assertEquals(Arrays.asList("Book1", "Mrinal", "1996", "Book2", "Arpan", "1997"),
                library.getItemDetails(TypeOfLibraryItem.BOOK));
        library.checkOut(new Title("Book1"), TypeOfLibraryItem.BOOK);
        assertEquals(Arrays.asList("Book2", "Arpan", "1997"), library.getItemDetails(TypeOfLibraryItem.BOOK));
        library.logout();
        assertTrue(library.login(libraryNumber2, password2));
        library.returnItem(new Title("Book1"), TypeOfLibraryItem.BOOK);
        assertEquals(Arrays.asList("Book2", "Arpan", "1997"), library.getItemDetails(TypeOfLibraryItem.BOOK));
    }
}