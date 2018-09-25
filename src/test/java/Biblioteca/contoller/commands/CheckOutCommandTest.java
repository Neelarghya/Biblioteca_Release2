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

class CheckOutCommandTest {
    private OutputDriver outputDriver;
    private InputDriver inputDriver;
    private Library library;
    private Command commandBook, commandMovie;

    @BeforeEach
    void init() {
        commandBook = new CheckOutCommand(TypeOfLibraryItem.BOOK);
        commandMovie = new CheckOutCommand(TypeOfLibraryItem.MOVIE);
        outputDriver = mock(OutputDriver.class);
        inputDriver = mock(InputDriver.class);
        library = mock(Library.class);
    }

    @DisplayName("Should checkOut Book1 on check out")
    @Test
    void testActCheckOutBook() {
        when(inputDriver.getInput()).thenReturn("Book1");
        when(library.contains(new Title("Book1"), TypeOfLibraryItem.BOOK)).thenReturn(true);

        commandBook.perform(library, outputDriver, inputDriver);

        verify(outputDriver).print("Enter name of the book you want to check out: ");
        verify(library).checkOut(new Title("Book1"), TypeOfLibraryItem.BOOK);
    }

    @DisplayName("Should checkOut Book1 on check out if library has the book and show message")
    @Test
    void testActCheckOutBookSuccessful() {
        when(inputDriver.getInput()).thenReturn("Book1");
        when(library.contains(new Title("Book1"), TypeOfLibraryItem.BOOK)).thenReturn(true);

        commandBook.perform(library, outputDriver, inputDriver);

        verify(library).checkOut(new Title("Book1"), TypeOfLibraryItem.BOOK);
        verify(outputDriver).println("Thank you! Enjoy the book");
    }

    @DisplayName("Should not checkOut Book not in library, on check out and not show successful message")
    @Test
    void testNotRemoveCheckOutBook() {
        when(inputDriver.getInput()).thenReturn("Book not in Library");
        when(library.contains(new Title("Book not in library"), TypeOfLibraryItem.BOOK)).thenReturn(false);

        commandBook.perform(library, outputDriver, inputDriver);

        verify(library, times(0)).checkOut(new Title("Book not in Library"), TypeOfLibraryItem.BOOK);
        verify(outputDriver, times(0)).println("Thank you! Enjoy the book");
    }

    @DisplayName("Should not checkOut Book not in library, on check out and show unsuccessful message")
    @Test
    void testNotRemoveCheckBookOutUnsuccessful() {
        when(inputDriver.getInput()).thenReturn("Book not in Library");
        when(library.contains(new Title("Book not in Library"), TypeOfLibraryItem.BOOK)).thenReturn(false);

        commandBook.perform(library, outputDriver, inputDriver);

        verify(library, times(0)).checkOut(new Title("Book not in Library"), TypeOfLibraryItem.BOOK);
        verify(outputDriver).println("That book is not available.");
    }


    @DisplayName("Should checkOut Movie1 on check out")
    @Test
    void testActCheckOutMovie() {
        when(inputDriver.getInput()).thenReturn("Movie1");
        when(library.contains(new Title("Movie1"), MOVIE)).thenReturn(true);

        commandMovie.perform(library, outputDriver, inputDriver);

        verify(outputDriver).print("Enter name of the movie you want to check out: ");
        verify(library).checkOut(new Title("Movie1"), MOVIE);
    }

    @DisplayName("Should checkOut Movie1 on check out if library has the movie and show message")
    @Test
    void testActCheckOutMovieSuccessful() {
        when(inputDriver.getInput()).thenReturn("Movie1");
        when(library.contains(new Title("Movie1"), MOVIE)).thenReturn(true);

        commandMovie.perform(library, outputDriver, inputDriver);

        verify(library).checkOut(new Title("Movie1"), MOVIE);
        verify(outputDriver).println("Thank you! Enjoy the movie");
    }

    @DisplayName("Should not checkOut Movie not in library, on check out and not show successful message")
    @Test
    void testNotRemoveCheckOutMovie() {
        when(inputDriver.getInput()).thenReturn("Movie not in Library");
        when(library.contains(new Title("Movie not in library"), MOVIE)).thenReturn(false);

        commandMovie.perform(library, outputDriver, inputDriver);

        verify(library, times(0)).checkOut(new Title("Movie not in Library"), MOVIE);
        verify(outputDriver, times(0)).println("Thank you! Enjoy the movie");
    }

    @DisplayName("Should not checkOut Movie not in library, on check out and show unsuccessful message")
    @Test
    void testNotRemoveCheckOutMovieUnsuccessful() {
        when(inputDriver.getInput()).thenReturn("Movie not in Library");
        when(library.contains(new Title("Movie not in Library"), MOVIE)).thenReturn(false);

        commandMovie.perform(library, outputDriver, inputDriver);

        verify(library, times(0)).checkOut(new Title("Movie not in Library"), MOVIE);
        verify(outputDriver).println("That movie is not available.");
    }
}