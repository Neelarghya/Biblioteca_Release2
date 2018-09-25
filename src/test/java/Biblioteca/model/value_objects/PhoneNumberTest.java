package Biblioteca.model.value_objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {
    @DisplayName("number of 9191991 = '9191991' ")
    @Test
    void testGetNumber1(){
        assertEquals("9191991", new PhoneNumber(9191991).getNumber());
    }

    @DisplayName("number of 5 = '5' ")
    @Test
    void testGetNumber2(){
        assertEquals("5", new PhoneNumber(5).getNumber());
    }
}