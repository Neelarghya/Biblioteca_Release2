package Biblioteca.model.value_objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PasswordTest {
    private Password password1;
    private Password password2;

    @BeforeEach
    void init() {
        password1 = new Password("abc");
        password2 = new Password("bcd");
    }

    @DisplayName("abc = abc")
    @Test
    void testEquals1() {
        assertEquals(new Password("abc"), password1);
    }

    @DisplayName("bcd = bcd")
    @Test
    void testEquals2() {
        assertEquals(new Password("bcd"), password2);
    }

    @DisplayName("abc not = bcd")
    @Test
    void testNotEquals() {
        assertNotEquals(password1, password2);
    }
}