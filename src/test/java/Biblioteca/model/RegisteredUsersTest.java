package Biblioteca.model;

import Biblioteca.model.value_objects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RegisteredUsersTest {
    private final LibraryNumber libraryNumber1 = new LibraryNumber("111-1111");
    private final Password abc = new Password("abc");
    private final LibraryNumber libraryNumber2 = new LibraryNumber("222-2222");
    private final Password bcd = new Password("bcd");
    private final LibraryNumber libraryNumber3 = new LibraryNumber("333-3333");
    private final Password efg = new Password("efg");
    private final Customer customer1 = new Customer(libraryNumber1, abc, new Person("name"), new EmailAddress("default@gmail.com"), new PhoneNumber(1234567890));
    private final Customer customer2 = new Customer(libraryNumber2, bcd, new Person("name"), new EmailAddress("default@gmail.com"), new PhoneNumber(1234567890));
    private final Customer customerNotRegistered = new Customer(libraryNumber3, efg, new Person("name"), new EmailAddress("default@gmail.com"), new PhoneNumber(1234567890));
    private RegisteredUsers registeredUsers;

    @BeforeEach
    void init() {
        registeredUsers = new RegisteredUsers(Arrays.asList(customer1, customer2));
    }

    @DisplayName("Successful login returns true")
    @Test
    void testSuccess1() {
        assertTrue(registeredUsers.login(libraryNumber1, abc));
    }

    @DisplayName("Successful login returns true")
    @Test
    void testSuccess2() {
        assertTrue(registeredUsers.login(libraryNumber2, bcd));
    }

    @DisplayName("Unsuccessful login returns false")
    @Test
    void testNoSuccess1() {
        assertFalse(registeredUsers.login(libraryNumber2, abc));
    }

    @DisplayName("Successful login returns false")
    @Test
    void testNoSuccess2() {
        assertFalse(registeredUsers.login(libraryNumber1, bcd));
    }

    @DisplayName("Customer not registered should not be able to login")
    @Test
    void testNoSuccessUnregistered() {
        assertFalse(registeredUsers.login(libraryNumber3, efg));
    }

    @DisplayName("Current user should be null if no one has logged in")
    @Test
    void testCurrentUserNull() {
        assertNull(registeredUsers.getLoggedInUser());
    }

    @DisplayName("Successful login should set the the current user to the correct customer")
    @Test
    void testSuccessShouldSetCurrentUser1() {
        registeredUsers.login(libraryNumber1, abc);
        assertEquals(customer1, registeredUsers.getLoggedInUser());
    }

    @DisplayName("Successful login should set the the current user to the correct customer")
    @Test
    void testSuccessShouldSetCurrentUser2() {
        registeredUsers.login(libraryNumber2, bcd);
        assertEquals(customer2, registeredUsers.getLoggedInUser());
    }

    @DisplayName("Unsuccessful login should  not set the the current user")
    @Test
    void testNoSuccessShouldNotSet() {
        registeredUsers.login(libraryNumber3, bcd);
        assertNull(registeredUsers.getLoggedInUser());
    }

    @DisplayName("Should not set user that's not registered")
    @Test
    void testNotSetUnregistered() {
        registeredUsers.login(libraryNumber3, efg);
        assertNotEquals(customerNotRegistered, registeredUsers.getLoggedInUser());
    }

    @DisplayName("Should log out currently logged in user")
    @Test
    void testLogout() {
        registeredUsers.login(libraryNumber1, abc);
        assertEquals(customer1, registeredUsers.getLoggedInUser());
        registeredUsers.logout();
        assertNull(registeredUsers.getLoggedInUser());
    }
}