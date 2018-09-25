package Biblioteca.model.value_objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RatingTest {
    @DisplayName("8 = 8")
    @Test
    void testDisplayRating8() {
        Rating rating = new Rating(8);
        assertEquals("8", rating.displayRating());
    }

    @DisplayName("10 = 10")
    @Test
    void testDisplayRating10() {
        Rating rating = new Rating(10);
        assertEquals("10", rating.displayRating());
    }

    @DisplayName("1 = 1")
    @Test
    void testDisplayRating1() {
        Rating rating = new Rating(1);
        assertEquals("1", rating.displayRating());
    }

    @DisplayName("0 = Unrated")
    @Test
    void testDisplayRating0() {
        Rating rating = new Rating(0);
        assertEquals("Unrated", rating.displayRating());
    }

    @DisplayName("11 = Unrated")
    @Test
    void testDisplayRating11() {
        Rating rating = new Rating(11);
        assertEquals("Unrated", rating.displayRating());
    }
}