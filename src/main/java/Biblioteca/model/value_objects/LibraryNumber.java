package Biblioteca.model.value_objects;

import java.util.Objects;

// Part of credential that uniquely identifies a user
public class LibraryNumber {
    private final String value;

    public LibraryNumber(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LibraryNumber libraryNumber = (LibraryNumber) o;
        return value.equals(libraryNumber.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
