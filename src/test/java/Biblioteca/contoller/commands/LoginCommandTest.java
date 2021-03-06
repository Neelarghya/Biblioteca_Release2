package Biblioteca.contoller.commands;

import Biblioteca.model.Customer;
import Biblioteca.model.Library;
import Biblioteca.model.value_objects.*;
import Biblioteca.view.InputDriver;
import Biblioteca.view.OutputDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginCommandTest {
    private OutputDriver outputDriver;
    private InputDriver inputDriver;
    private Library library;
    private Command command;
    private Customer customer1, customer2;

    @BeforeEach
    void init() {
        command = new LoginCommand();
        outputDriver = mock(OutputDriver.class);
        inputDriver = mock(InputDriver.class);
        customer1 = new Customer(new LibraryNumber("1"), new Password("1"), new Person("name"), new EmailAddress("default@gmail.com"), new PhoneNumber(1234567890));
        customer2 = new Customer(new LibraryNumber("2"), new Password("2"), new Person("name"), new EmailAddress("default@gmail.com"), new PhoneNumber(1234567890));
        library = new Library(null, Arrays.asList(customer1, customer2));
    }

    @DisplayName("Login command should login user")
    @Test
    void testLogin1() {
        when(inputDriver.getInput()).thenReturn("1");
        assertNull(library.getCurrentUser());
        command.perform(library, outputDriver, inputDriver);
        assertEquals(customer1, library.getCurrentUser());
    }

    @DisplayName("Login command should login user")
    @Test
    void testLogin2() {
        when(inputDriver.getInput()).thenReturn("2");
        assertNull(library.getCurrentUser());
        command.perform(library, outputDriver, inputDriver);
        assertEquals(customer2, library.getCurrentUser());
    }
}