package Biblioteca.contoller;

import Biblioteca.model.*;
import Biblioteca.model.value_objects.Person;
import Biblioteca.model.value_objects.Rating;
import Biblioteca.model.value_objects.Title;
import Biblioteca.model.value_objects.Year;
import Biblioteca.view.InputDriver;
import Biblioteca.view.OutputDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Biblioteca.model.TypeOfLibraryItem.BOOK;
import static Biblioteca.model.TypeOfLibraryItem.MOVIE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MenuTest {
    private OutputDriver outputDriver;
    private InputDriver inputDriver;
    private Book book1, book2, libraryItemsNotInLibrary;
    private Movie movie1, movie2;
    private Library library;
    private List<LibraryItem> libraryItems;
    private List<Customer> customers = new ArrayList<>();


    @BeforeEach
    void init() {
        outputDriver = mock(OutputDriver.class);
        inputDriver = mock(InputDriver.class);
        library = mock(Library.class);

        book1 = new Book(new Title("Book1"), new Person("Mrinal"), new Year(1996));
        book2 = new Book(new Title("Book2"), new Person("Arpan"), new Year(1997));
        movie1 = new Movie(new Title("Movie1"), new Person("Sridhar"),
                new Year(1896), new Rating(6));
        movie2 = new Movie(new Title("Movie2"), new Person("Bari"),
                new Year(1897), new Rating(11));
        libraryItemsNotInLibrary = new Book(new Title("Book not in library"),
                new Person("Nikhil"), new Year(1897));

        libraryItems = new ArrayList<>();
        libraryItems.add(book1);
        libraryItems.add(book2);
        libraryItems.add(movie1);
        libraryItems.add(movie2);
    }

    @DisplayName("Enter 1 to List Books should be displayed as menu option")
    @Test
    void testGetMenuListBooks() {
        assertEquals("Enter 1 to List Books", Menu.LIST_BOOKS.getMenu(1));
    }

    @DisplayName("Should list libraryItems")
    @Test
    void testPerformListBooks() {
        library = new Library(libraryItems, customers);
        Menu.LIST_BOOKS.perform(library, outputDriver, inputDriver);
        verify(outputDriver).printInColumns(Arrays.asList(
                "Book1", "Mrinal", "1996", "Book2", "Arpan", "1997"), BOOK.getNumberOfFields());
    }

    @DisplayName("Enter 2 to List Movies should be displayed as menu option")
    @Test
    void testGetMenuListMovies() {
        assertEquals("Enter 2 to List Movies", Menu.LIST_MOVIES.getMenu(2));
    }

    @DisplayName("Should list libraryItems")
    @Test
    void testPerformListMovies() {
        library = new Library(libraryItems, customers);
        Menu.LIST_MOVIES.perform(library, outputDriver, inputDriver);
        verify(outputDriver).printInColumns(Arrays.asList(
                "Movie1", "Sridhar", "1896", "6", "Movie2", "Bari", "1897", "Unrated"), MOVIE.getNumberOfFields());
    }

    @DisplayName("Enter 0 to Quit should be displayed as menu option")
    @Test
    void testGetMenuQuit() {
        assertEquals("Enter 0 to Quit", Menu.QUIT.getMenu(0));
    }

    @DisplayName("Should list libraryItems")
    @Test
    void testPerformQuit() {
        Menu.QUIT.perform(library, outputDriver, inputDriver);
        verify(outputDriver).println("Bye... Come again");
    }

    @DisplayName("Enter 3 to Check Out a book should be displayed as menu option")
    @Test
    void testGetMenuCheckOutBook() {
        assertEquals("Enter 3 to Check Out a book", Menu.CHECK_OUT_BOOK.getMenu(3));
    }

    @DisplayName("Should checkOut Book1 on check out if library has the book and show message")
    @Test
    void testPerformCheckOutBookSuccessful() {
        when(inputDriver.getInput()).thenReturn("Book1");
        when(library.contains(new Title("Book1"), BOOK)).thenReturn(true);

        Menu.CHECK_OUT_BOOK.perform(library, outputDriver, inputDriver);

        verify(library).checkOut(new Title("Book1"), BOOK);
        verify(outputDriver).println("Thank you! Enjoy the book");
    }

    @DisplayName("Should not checkOut Book not in library, on check out and show unsuccessful message")
    @Test
    void testNotRemoveCheckOutUnsuccessful() {
        when(inputDriver.getInput()).thenReturn("Book not in Library");
        when(library.contains(new Title("Book not in Library"), BOOK)).thenReturn(false);

        Menu.CHECK_OUT_BOOK.perform(library, outputDriver, inputDriver);

        verify(library, times(0)).checkOut(new Title("Book not in Library"), BOOK);
        verify(outputDriver).println("That book is not available.");
    }

    @DisplayName("Enter 4 to Check Out a movie should be displayed as menu option")
    @Test
    void testGetMenuCheckOutMovie() {
        assertEquals("Enter 4 to Check Out a movie", Menu.CHECK_OUT_MOVIE.getMenu(4));
    }

    @DisplayName("Should checkOut Book1 on check out if library has the book and show message")
    @Test
    void testPerformCheckOutMovieSuccessful() {
        when(inputDriver.getInput()).thenReturn("Movie1");
        when(library.contains(new Title("Movie1"), MOVIE)).thenReturn(true);

        Menu.CHECK_OUT_MOVIE.perform(library, outputDriver, inputDriver);

        verify(library).checkOut(new Title("Movie1"), MOVIE);
        verify(outputDriver).println("Thank you! Enjoy the movie");
    }

    @DisplayName("Should not checkOut Movie not in library, on check out and show unsuccessful message")
    @Test
    void testNotRemoveCheckOutMovieUnsuccessful() {
        when(inputDriver.getInput()).thenReturn("Movie not in Library");
        when(library.contains(new Title("Movie not in Library"), MOVIE)).thenReturn(false);

        Menu.CHECK_OUT_MOVIE.perform(library, outputDriver, inputDriver);

        verify(library, times(0)).checkOut(new Title("Movie not in Library"), MOVIE);
        verify(outputDriver).println("That movie is not available.");
    }

    @DisplayName("Enter 5 to Return a book should be displayed as menu option")
    @Test
    void testGetMenuReturnBook() {
        assertEquals("Enter 5 to Return a book", Menu.RETURN_BOOK.getMenu(5));
    }

    @DisplayName("Should return Book1 on and show message")
    @Test
    void testPerformReturnBookSuccessful() {
        when(inputDriver.getInput()).thenReturn("Book1");

        when(library.hasCheckedOut(new Title("Book1"), BOOK)).thenReturn(true);
        Menu.RETURN_BOOK.perform(library, outputDriver, inputDriver);

        verify(library).returnItem(new Title("Book1"), BOOK);
        verify(outputDriver).println("Thank you for returning the book.");
    }

    @DisplayName("Should not be able to return Book not in checked out, show unsuccessful message")
    @Test
    void testNotAddReturnBookUnsuccessful() {
        when(inputDriver.getInput()).thenReturn("Book not in Library");
        when(library.hasCheckedOut(new Title("Book not in Library"), BOOK)).thenReturn(false);

        Menu.RETURN_BOOK.perform(library, outputDriver, inputDriver);

        verify(outputDriver).print("Enter name of the book you want to return: ");
        verify(library, times(0)).returnItem(new Title("Book not in Library"), BOOK);
        verify(outputDriver).println("That is not a valid book to return.");
    }

    @DisplayName("Enter 6 to Return a movie should be displayed as menu option")
    @Test
    void testGetMenuReturnMovie() {
        assertEquals("Enter 6 to Return a movie", Menu.RETURN_MOVIE.getMenu(6));
    }

    @DisplayName("Should return Book1 on and show message")
    @Test
    void testPerformReturnMovieSuccessful() {
        when(inputDriver.getInput()).thenReturn("Movie1");

        when(library.hasCheckedOut(new Title("Movie1"), MOVIE)).thenReturn(true);
        Menu.RETURN_MOVIE.perform(library, outputDriver, inputDriver);

        verify(library).returnItem(new Title("Movie1"), MOVIE);
        verify(outputDriver).println("Thank you for returning the movie.");
    }

    @DisplayName("Should not be able to return Movie not in checked out, show unsuccessful message")
    @Test
    void testNotAddReturnMovieUnsuccessful() {
        when(inputDriver.getInput()).thenReturn("Movie not in Library");
        when(library.hasCheckedOut(new Title("Movie not in Library"), MOVIE)).thenReturn(false);

        Menu.RETURN_MOVIE.perform(library, outputDriver, inputDriver);

        verify(outputDriver).print("Enter name of the movie you want to return: ");
        verify(library, times(0)).returnItem(new Title("Movie not in Library"), MOVIE);
        verify(outputDriver).println("That is not a valid movie to return.");
    }

    @DisplayName("Should display 'Select a valid option!' for INVALID OPTION")
    @Test
    void testPerformInvalidOption() {
        Menu.INVALID_OPTION.perform(library, outputDriver, inputDriver);
        verify(outputDriver).println("Select a valid option!");
    }
}