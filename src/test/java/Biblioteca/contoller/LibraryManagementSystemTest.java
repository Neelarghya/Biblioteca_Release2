package Biblioteca.contoller;

import Biblioteca.model.TypeOfLibraryItem;
import Biblioteca.model.Book;
import Biblioteca.model.Customer;
import Biblioteca.model.Library;
import Biblioteca.model.LibraryItem;
import Biblioteca.model.value_objects.*;
import Biblioteca.view.InputDriver;
import Biblioteca.view.OutputDriver;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class LibraryManagementSystemTest {
    private LibraryManagementSystem libraryManagementSystem;
    private OutputDriver outputDriver;
    private InputDriver inputDriver;

    @BeforeEach
    void init() {
        outputDriver = mock(OutputDriver.class);
        inputDriver = mock(InputDriver.class);
        when(inputDriver.getMenuInput()).thenReturn(1).thenReturn(0);
        Book book1 = new Book(new Title("Book1"), new Person("Mrinal"), new Year(1996));
        Book book2 = new Book(new Title("Book2"), new Person("Arpan"), new Year(1997));
        final List<LibraryItem> libraryItems = new ArrayList<>();
        libraryItems.add(book1);
        libraryItems.add(book2);
        List<Customer> customers = Arrays.asList(
                new Customer(new LibraryNumber("1"), new Password("1"), new Person("Bari"), new EmailAddress("bari@gmail.com"), new PhoneNumber(1234890)),
                new Customer(new LibraryNumber("2"), new Password("2"), new Person("Neel"), new EmailAddress("Neel@gmail.com"), new PhoneNumber(34567890)),
                new Customer(new LibraryNumber("3"), new Password("3"), new Person("Nikhil"), new EmailAddress("nikhil@gmail.com"), new PhoneNumber(123456))
        );
        final Library library = new Library(libraryItems, customers);
        libraryManagementSystem = new LibraryManagementSystem(library, outputDriver, inputDriver);
    }

    @DisplayName("LibraryManagementSystem should welcome customer")
    @Test
    void testWelcome() {
        verifyZeroInteractions(outputDriver);
        libraryManagementSystem.start();
        verify(outputDriver).println("Welcome Customer to Biblioteca");
    }

    @DisplayName("Should println all the books")
    @Test
    void testDisplayingBookList() {
        verifyZeroInteractions(outputDriver);
        libraryManagementSystem.start();
        verify(outputDriver).printInColumns(Arrays.asList(
                "Book1", "Mrinal", "1996", "Book2", "Arpan", "1997"), TypeOfLibraryItem.BOOK.getNumberOfFields());
    }

    @DisplayName("Should not println books not in the system")
    @Test
    void testDisplayingBookListNotDisplayThoseNotIn() {
        libraryManagementSystem.start();
        verify(outputDriver, times(0))
                .printInColumns(Arrays.asList("bookNotInLibrary"), TypeOfLibraryItem.BOOK.getNumberOfFields());
    }

    @DisplayName("Should show 'Select a valid option!' when an option more than number of menus is given")
    @Test
    void testInvalidOptionForGreater() {
        verifyZeroInteractions(outputDriver);
        when(inputDriver.getMenuInput()).thenReturn(Menu.values().length).thenReturn(0);
        libraryManagementSystem.start();
        verify(outputDriver).println("Select a valid option!");
    }

    @DisplayName("Should show 'Select a valid option!' when selecting option less than 0")
    @Test
    void testInvalidOptionForLess() {
        verifyZeroInteractions(outputDriver);
        when(inputDriver.getMenuInput()).thenReturn(-1).thenReturn(0);
        libraryManagementSystem.start();
        verify(outputDriver).println("Select a valid option!");
    }

    @DisplayName("Should remove book that's checked out")
    @Test
    void testCheckOutBook1() {
        verifyZeroInteractions(outputDriver);
        when(inputDriver.getInput()).thenReturn("1").thenReturn("1").thenReturn("Book1");
        when(inputDriver.getMenuInput()).thenReturn(3).thenReturn(3).thenReturn(1).thenReturn(0);
        libraryManagementSystem.start();
        verify(outputDriver).printInColumns(Arrays.asList("Book2", "Arpan", "1997"), TypeOfLibraryItem.BOOK.getNumberOfFields());
    }

    @AfterEach
    void cleanUp() {
        System.setIn(System.in);
    }
}