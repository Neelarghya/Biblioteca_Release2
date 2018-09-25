package Biblioteca.model.value_objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailAddressTest {
    @DisplayName("get address abc@g.com will be abc@g.com")
    @Test
    void testGetAddress1(){
        assertEquals("abc@g.com", new EmailAddress("abc@g.com").getAddress());
    }

    @DisplayName("get address efg@g.com will be efg@g.com")
    @Test
    void testGetAddress2(){
        assertEquals("efg@g.com", new EmailAddress("efg@g.com").getAddress());
    }
}