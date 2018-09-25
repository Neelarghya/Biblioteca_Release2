package Biblioteca.model.value_objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person mrinal, arpan;

    @BeforeEach
    void init(){
        mrinal = new Person("Mrinal");
        arpan = new Person("Arpan");
    }
    @DisplayName("get Mrinal when converting author name Mrinal to String")
    @Test
    void testToStringMrinal(){
        assertEquals("Mrinal",mrinal.toString());
    }

    @DisplayName("get Arpan when converting author name Arpan to String")
    @Test
    void testGetNameArpan(){
        assertEquals("Arpan", arpan.toString());
    }

    @DisplayName("get Mrinal when converting author name Mrinal to String")
    @Test
    void testGetNameMrinal(){
        assertEquals("Mrinal", mrinal.getName());
    }

    @DisplayName("get Arpan when converting author name Arpan to String")
    @Test
    void testToStringArpan(){
        assertEquals("Arpan", arpan.getName());
    }

    @DisplayName("Mrinal = Mrinal")
    @Test
    void testEqualsMrinal(){
        assertEquals(new Person("Mrinal"), mrinal);
    }

    @DisplayName("get Arpan when converting author name Arpan to String")
    @Test
    void testEqualsArpan(){
        assertEquals(new Person("Arpan"), arpan);
    }

    @DisplayName("get Arpan when converting author name Arpan to String")
    @Test
    void testNotEquals(){
        assertNotEquals(new Person("Mrinal"), arpan);
    }
}