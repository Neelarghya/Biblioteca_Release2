package Biblioteca.model;

import Biblioteca.model.value_objects.Person;
import Biblioteca.model.value_objects.Rating;
import Biblioteca.model.value_objects.Title;
import Biblioteca.model.value_objects.Year;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Biblioteca.model.TypeOfLibraryItem.BOOK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LibraryItemTest {
    private LibraryItem book1, book2, movie;

    @BeforeEach
    void init() {
        book1 = new Book(new Title("Book1"), new Person("Mrinal"),
                new Year(1996));
        book2 = new Book(new Title("Book2"), new Person("Arpan"),
                new Year(1995));
        movie = new Movie(new Title("Book2"), new Person("Arpan"),
                new Year(1995), new Rating(8));
    }

    @DisplayName("Should return Book1")
    @Test
    void testGetTitle() {
        assertEquals(new Title("Book1"),
                book1.getTitle());
    }

    @DisplayName("Should return Book2")
    @Test
    void testGetBook2() {
        assertEquals(new Title("Book2"),
                book2.getTitle());
    }

    @DisplayName("Should not return Book1")
    @Test
    void testNotGetTitle() {
        assertNotEquals(new Title("Book1"),
                book2.getTitle());
    }

    @DisplayName("Should return Mrinal")
    @Test
    void testGetAuthor1() {
        assertEquals(new Person("Mrinal"),
                book1.getPerson());
    }

    @DisplayName("Should return Arpan")
    @Test
    void testGetAuthor2() {
        assertEquals(new Person("Arpan"),
                book2.getPerson());
    }

    @DisplayName("Should not return Mrinal")
    @Test
    void testNotGetAuthor() {
        assertNotEquals(new Title("Mrinal"),
                book2.getPerson());
    }

    @DisplayName("Should return 1996")
    @Test
    void testGetYear1() {
        assertEquals(new Year(1996),
                book1.getYear());
    }

    @DisplayName("Should return 1995")
    @Test
    void testGetYear2() {
        assertEquals(new Year(1995),
                book2.getYear());
    }

    @DisplayName("Should not return 1996")
    @Test
    void testNotGetYear() {
        assertNotEquals(new Year(1996),
                book2.getYear());
    }

    @DisplayName("Book1 = Book1")
    @Test
    void testEquals() {
        assertEquals(BOOK.createLibraryItemForTitle(new Title("Book1")), book1);
    }

    @DisplayName("Book1 not = Book2")
    @Test
    void testNotEquals() {
        assertNotEquals(BOOK.createLibraryItemForTitle(new Title("Book1")), book2);
    }

    @DisplayName("Book1(Movie) not = Book1(Book)")
    @Test
    void testNotEqualsIfTypeUnequal() {
        assertNotEquals(movie, book1);
    }
}