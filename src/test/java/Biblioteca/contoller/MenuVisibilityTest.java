package Biblioteca.contoller;

import Biblioteca.model.Customer;
import Biblioteca.model.value_objects.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuVisibilityTest {
    private LibraryNumber libraryNumber = new LibraryNumber("111-1111");
    private final Password password = new Password("abc");
    private final Customer customer = new Customer(libraryNumber, password, new Person("name"), new EmailAddress("default@gmail.com"), new PhoneNumber(1234567890));

    @DisplayName("Should display when there is no ones logged in")
    @Test
    void testAlwaysNull() {
        assertTrue(MenuVisibility.ALWAYS.isDisplayable(null));
    }

    @DisplayName("Should display when there is a current user")
    @Test
    void testAlwaysCustomer() {
        assertTrue(MenuVisibility.ALWAYS.isDisplayable(customer));
    }

    @DisplayName("Should not display when there is no current user")
    @Test
    void testNeverNull() {
        assertFalse(MenuVisibility.NEVER.isDisplayable(null));
    }

    @DisplayName("Should not display when there is a current user")
    @Test
    void testNeverUser() {
        assertFalse(MenuVisibility.NEVER.isDisplayable(customer));
    }

    @DisplayName("Should not display when there is no current user")
    @Test
    void testLoggedInNull() {
        assertFalse(MenuVisibility.LOGGED_IN.isDisplayable(null));
    }

    @DisplayName("Should display when there is a current user")
    @Test
    void testLoggedInUser() {
        assertTrue(MenuVisibility.LOGGED_IN.isDisplayable(customer));
    }

    @DisplayName("Should display when there is no current user")
    @Test
    void testLoggedOutNull() {
        assertTrue(MenuVisibility.LOGGED_OUT.isDisplayable(null));
    }

    @DisplayName("Should not display when there is a current user")
    @Test
    void testLoggedOutUser() {
        assertFalse(MenuVisibility.LOGGED_OUT.isDisplayable(customer));
    }
}