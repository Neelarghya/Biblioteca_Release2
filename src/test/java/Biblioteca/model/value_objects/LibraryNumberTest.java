package Biblioteca.model.value_objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LibraryNumberTest {
    private LibraryNumber libraryNumber1;
    private LibraryNumber libraryNumber2;

    @BeforeEach
    void init() {
        libraryNumber1 = new LibraryNumber("111-1111");
        libraryNumber2 = new LibraryNumber("222-2222");
    }

    @DisplayName("111-1111 = 111-1111")
    @Test
    void testEquals1() {
        assertEquals(new LibraryNumber("111-1111"), libraryNumber1);
    }

    @DisplayName("222-2222 = 222-2222")
    @Test
    void testEquals2() {
        assertEquals(new LibraryNumber("222-2222"), libraryNumber2);
    }

    @DisplayName("111-1111 not = 222-2222")
    @Test
    void testNotEquals() {
        assertNotEquals(libraryNumber1, libraryNumber2);
    }
}