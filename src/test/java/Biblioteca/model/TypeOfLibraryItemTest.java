package Biblioteca.model;

import Biblioteca.model.value_objects.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypeOfLibraryItemTest {
    @DisplayName("Books should have 3 fields")
    @Test
    void testNumberOfFieldsBook() {
        assertEquals(3, TypeOfLibraryItem.BOOK.getNumberOfFields());
    }

    @DisplayName("Movies should have 4 fields")
    @Test
    void testNumberOfFieldsMovie() {
        assertEquals(4, TypeOfLibraryItem.MOVIE.getNumberOfFields());
    }

    @DisplayName("Should be able to create a new Book")
    @Test
    void testCreateNewBook() {
        assertEquals(new Book(new Title("Book1"), null, null),
                TypeOfLibraryItem.BOOK.createLibraryItemForTitle(new Title("Book1")));
    }

    @DisplayName("Should be able to create a new Book")
    @Test
    void testCreateNewMovie() {
        assertEquals(new Movie(new Title("Movie1"), null, null, null),
                TypeOfLibraryItem.MOVIE.createLibraryItemForTitle(new Title("Movie1")));
    }
}