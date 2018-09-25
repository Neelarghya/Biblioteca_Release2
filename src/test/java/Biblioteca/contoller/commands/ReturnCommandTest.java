package Biblioteca.contoller.commands;

import Biblioteca.model.TypeOfLibraryItem;
import Biblioteca.model.Library;
import Biblioteca.model.value_objects.Title;
import Biblioteca.view.InputDriver;
import Biblioteca.view.OutputDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Biblioteca.model.TypeOfLibraryItem.MOVIE;
import static org.mockito.Mockito.*;

class ReturnCommandTest {
    private OutputDriver outputDriver;
    private InputDriver inputDriver;
    private Library library;
    private Command commandBook, commandMovie;

    @BeforeEach
    void init() {
        commandBook = new ReturnCommand(TypeOfLibraryItem.BOOK);
        commandMovie = new ReturnCommand(TypeOfLibraryItem.MOVIE);
        outputDriver = mock(OutputDriver.class);
        inputDriver = mock(InputDriver.class);
        library = mock(Library.class);
    }

    @DisplayName("Should bea able to return Book1 after check out")
    @Test
    void testPerformReturnBook() {
        when(inputDriver.getInput()).thenReturn("Book1");

        when(library.hasCheckedOut(new Title("Book1"), TypeOfLibraryItem.BOOK)).thenReturn(true);
        commandBook.perform(library, outputDriver, inputDriver);

        verify(outputDriver).print("Enter name of the book you want to return: ");
        verify(library).returnItem(new Title("Book1"), TypeOfLibraryItem.BOOK);
    }

    @DisplayName("Should return Book1 on and show message")
    @Test
    void testPerformReturnBookSuccessful() {
        when(inputDriver.getInput()).thenReturn("Book1");

        when(library.hasCheckedOut(new Title("Book1"), TypeOfLibraryItem.BOOK)).thenReturn(true);
        commandBook.perform(library, outputDriver, inputDriver);

        verify(library).returnItem(new Title("Book1"), TypeOfLibraryItem.BOOK);
        verify(outputDriver).println("Thank you for returning the book.");
    }

    @DisplayName("Should not return Book not checked out and not show unsuccessful message")
    @Test
    void testNotAddReturnBook() {
        when(inputDriver.getInput()).thenReturn("Book not in Library");
        when(library.hasCheckedOut(new Title("Book not in library"), TypeOfLibraryItem.BOOK)).thenReturn(false);

        commandBook.perform(library, outputDriver, inputDriver);

        verify(library, times(0)).returnItem(new Title("Book not in Library"), TypeOfLibraryItem.BOOK);
        verify(outputDriver).println("That is not a valid book to return.");
    }

    @DisplayName("Should not be able to return Book not in checked out, show unsuccessful message")
    @Test
    void testNotAddReturnBookUnsuccessful() {
        when(inputDriver.getInput()).thenReturn("Book not in Library");
        when(library.hasCheckedOut(new Title("Book not in Library"), TypeOfLibraryItem.BOOK)).thenReturn(false);

        commandBook.perform(library, outputDriver, inputDriver);

        verify(outputDriver).print("Enter name of the book you want to return: ");
        verify(library, times(0)).returnItem(new Title("Book not in Library"), TypeOfLibraryItem.BOOK);
        verify(outputDriver).println("That is not a valid book to return.");
    }

    @DisplayName("Should bea able to return Movie1 after check out")
    @Test
    void testPerformReturnMovie() {
        when(inputDriver.getInput()).thenReturn("Movie1");

        when(library.hasCheckedOut(new Title("Movie1"), MOVIE)).thenReturn(true);
        commandMovie.perform(library, outputDriver, inputDriver);

        verify(outputDriver).print("Enter name of the movie you want to return: ");
        verify(library).returnItem(new Title("Movie1"), MOVIE);
    }

    @DisplayName("Should return Movie1 on and show message")
    @Test
    void testPerformReturnMovieSuccessful() {
        when(inputDriver.getInput()).thenReturn("Movie1");

        when(library.hasCheckedOut(new Title("Movie1"), MOVIE)).thenReturn(true);
        commandMovie.perform(library, outputDriver, inputDriver);

        verify(library).returnItem(new Title("Movie1"), MOVIE);
        verify(outputDriver).println("Thank you for returning the movie.");
    }

    @DisplayName("Should not return Movie not checked out and not show unsuccessful message")
    @Test
    void testNotAddReturnMovie() {
        when(inputDriver.getInput()).thenReturn("Movie not in Library");
        when(library.hasCheckedOut(new Title("Movie not in library"), MOVIE)).thenReturn(false);

        commandMovie.perform(library, outputDriver, inputDriver);

        verify(library, times(0)).returnItem(new Title("Movie not in Library"), MOVIE);
        verify(outputDriver).println("That is not a valid movie to return.");
    }

    @DisplayName("Should not be able to return Movie not in checked out, show unsuccessful message")
    @Test
    void testNotAddReturnMovieUnsuccessful() {
        when(inputDriver.getInput()).thenReturn("Movie not in Library");
        when(library.hasCheckedOut(new Title("Movie not in Library"), MOVIE)).thenReturn(false);

        commandMovie.perform(library, outputDriver, inputDriver);

        verify(outputDriver).print("Enter name of the movie you want to return: ");
        verify(library, times(0)).returnItem(new Title("Movie not in Library"), MOVIE);
        verify(outputDriver).println("That is not a valid movie to return.");
    }
}