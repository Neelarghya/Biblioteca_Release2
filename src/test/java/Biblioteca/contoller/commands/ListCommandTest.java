package Biblioteca.contoller.commands;

import Biblioteca.model.Library;
import Biblioteca.model.TypeOfLibraryItem;
import Biblioteca.view.InputDriver;
import Biblioteca.view.OutputDriver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static Biblioteca.model.TypeOfLibraryItem.BOOK;
import static Biblioteca.model.TypeOfLibraryItem.MOVIE;
import static org.mockito.Mockito.*;

class ListCommandTest {
    private Command commandBook = new ListCommand(TypeOfLibraryItem.BOOK);
    private Command commandMovie = new ListCommand(MOVIE);
    private Library library = mock(Library.class);
    private OutputDriver outputDriver = mock(OutputDriver.class);
    private InputDriver inputDriver = mock(InputDriver.class);

    @DisplayName("Should List what library returns as book details")
    @Test
    void testListBookDetails() {
        when(library.getItemDetails(BOOK)).thenReturn(Arrays.asList("Book Details"));
        commandBook.perform(library, outputDriver, inputDriver);

        verify(library).getItemDetails(BOOK);
        verify(outputDriver).printInColumns(library.getItemDetails(BOOK), BOOK.getNumberOfFields());
    }

    @DisplayName("Should List the Book details")
    @Test
    void testListBooks() {
        when(library.getItemDetails(BOOK)).thenReturn(Arrays.asList("Book1", "Mrinal", "1996", "Book2", "Arpan", "1997"));
        commandBook.perform(library, outputDriver, inputDriver);

        verify(library).getItemDetails(BOOK);
        verify(outputDriver).printInColumns(library.getItemDetails(BOOK), BOOK.getNumberOfFields());
    }

    @DisplayName("Should List what library returns as movie details")
    @Test
    void testListMovieDetails() {
        when(library.getItemDetails(MOVIE)).thenReturn(Arrays.asList("Movie Details"));
        commandMovie.perform(library, outputDriver, inputDriver);

        verify(library).getItemDetails(MOVIE);
        verify(outputDriver).printInColumns(library.getItemDetails(MOVIE), MOVIE.getNumberOfFields());
    }

    @DisplayName("Should List the Movie details")
    @Test
    void testListMovies() {
        when(library.getItemDetails(MOVIE)).thenReturn(Arrays.asList("Movie1", "Mrinal", "1996", "5", "Movie2", "Arpan", "1997", "Unrated"));
        commandMovie.perform(library, outputDriver, inputDriver);

        verify(library).getItemDetails(MOVIE);
        verify(outputDriver).printInColumns(library.getItemDetails(MOVIE), MOVIE.getNumberOfFields());
    }
}