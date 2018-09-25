package Biblioteca.contoller.commands;

import Biblioteca.common.Constants;
import Biblioteca.model.Customer;
import Biblioteca.model.Library;
import Biblioteca.model.value_objects.*;
import Biblioteca.view.InputDriver;
import Biblioteca.view.OutputDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DisplayUserDetailsCommandTest {
    private OutputDriver outputDriver;
    private InputDriver inputDriver;
    private Library library;
    private Command command;
    private Customer customer;

    @BeforeEach
    void init() {
        command = new DisplayUserDetailsCommand();
        outputDriver = mock(OutputDriver.class);
        inputDriver = mock(InputDriver.class);
        customer = new Customer(new LibraryNumber("1"), new Password("1"),
                new Person("Name1"), new EmailAddress("address@g.com"), new PhoneNumber(404));
        library = new Library(null, Arrays.asList(customer));
    }

    @DisplayName("Display logged in user's details")
    @Test
    void test() {
        library.login(new LibraryNumber("1"), new Password("1"));
        command.perform(library, outputDriver, inputDriver);

        verify(outputDriver).printInColumns(Arrays.asList("Name1", "address@g.com", "404"),
                Constants.NUMBER_OF_COLUMNS_IN_CUSTOMER_DETAILS);
    }
}