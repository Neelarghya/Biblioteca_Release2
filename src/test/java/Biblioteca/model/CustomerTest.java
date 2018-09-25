package Biblioteca.model;

import Biblioteca.model.value_objects.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static Biblioteca.model.TypeOfLibraryItem.BOOK;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private final LibraryNumber libraryNumber1 = new LibraryNumber("111-1111");
    private final Password abc = new Password("abc");
    private final LibraryNumber libraryNumber2 = new LibraryNumber("222-2222");
    private final Password bcd = new Password("bcd");
    private final Customer customer1 = new Customer(libraryNumber1, abc, new Person("name1"), new EmailAddress("default1@gmail.com"), new PhoneNumber(1));
    private final Customer customer2 = new Customer(libraryNumber2, bcd, new Person("name2"), new EmailAddress("default2@gmail.com"), new PhoneNumber(2));
    private final LibraryItem book1 = new Book(new Title("Book1"), new Person("Mrinal"), new Year(1996));
    private final LibraryItem book2 = new Book(new Title("Book2"), new Person("Arpan"), new Year(1997));

    @DisplayName("If credentials are correct return true")
    @Test
    void testCorrectCredentials1() {
        assertTrue(customer1.isCredentialsCorrect(libraryNumber1, abc));
    }

    @DisplayName("If credentials are correct return true")
    @Test
    void testCorrectCredentials2() {
        assertTrue(customer2.isCredentialsCorrect(libraryNumber2, bcd));
    }

    @DisplayName("If library number is not correct return false")
    @Test
    void testLibraryNumberNotCorrect() {
        assertFalse(customer1.isCredentialsCorrect(libraryNumber2, abc));
    }

    @DisplayName("If password1 is not correct return false")
    @Test
    void testPasswordNotCorrect() {
        assertFalse(customer1.isCredentialsCorrect(libraryNumber1, bcd));
    }

    @DisplayName("If both library number and password1 aren't correct return false")
    @Test
    void testBothNotCorrect() {
        assertFalse(customer1.isCredentialsCorrect(libraryNumber2, bcd));
    }

    @DisplayName("Should display complete Details of a Customer")
    @Test
    void testDetails1() {
        assertEquals(Arrays.asList("name1", "default1@gmail.com", "1"),
                customer1.getDetails());
    }

    @DisplayName("Should display complete Details of a Customer")
    @Test
    void testDetails2() {
        assertEquals(Arrays.asList("name2", "default2@gmail.com", "2"),
                customer2.getDetails());
    }

    @DisplayName("Should have checked book1 in after its checked")
    @Test
    void testHasCheckedBook1() {
        assertFalse(customer1.hasCheckedOut(BOOK.createLibraryItemForTitle(new Title("Book1"))));
        customer1.checkOut(book1);
        assertTrue(customer1.hasCheckedOut(book1));
    }

    @DisplayName("Should have checked book2 in after its checked")
    @Test
    void testHasCheckedBook2() {
        assertFalse(customer1.hasCheckedOut(book2));
        customer1.checkOut(book2);
        assertTrue(customer1.hasCheckedOut(book2));
    }

    @DisplayName("Should not have checked book not in library")
    @Test
    void testHasNotCheckedBook() {
        assertFalse(customer1.hasCheckedOut(book1));
    }

    @DisplayName("Should be able to return book checked out")
    @Test
    void testReturnBook1() {
        customer1.checkOut(book1);

        assertTrue(customer1.hasCheckedOut(book1));
        assertEquals(book1, customer1.returnItem(new Title("Book1"), BOOK));
        assertFalse(customer1.hasCheckedOut(book1));
    }

    @DisplayName("Should be able to return all books checked out")
    @Test
    void testReturnBook2() {
        customer1.checkOut(book1);
        customer1.checkOut(book2);

        assertTrue(customer1.hasCheckedOut(book1));
        assertTrue(customer1.hasCheckedOut(book2));

        assertEquals(book1, customer1.returnItem(new Title("Book1"), BOOK));
        assertFalse(customer1.hasCheckedOut(book1));
        assertTrue(customer1.hasCheckedOut(book2));

        assertEquals(book2, customer1.returnItem(new Title("Book2"), BOOK));
        assertFalse(customer1.hasCheckedOut(book2));
    }

    @DisplayName("Should be unchanged on trying to return book not checked out")
    @Test
    void testNotReturnBookIfNotCheckedOut() {
        assertNull(customer1.returnItem(new Title("Book1"), BOOK));
    }
}