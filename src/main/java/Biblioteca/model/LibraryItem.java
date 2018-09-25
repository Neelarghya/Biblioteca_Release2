package Biblioteca.model;

import Biblioteca.model.value_objects.Person;
import Biblioteca.model.value_objects.Title;
import Biblioteca.model.value_objects.Year;

import java.util.List;

// Things kept in the library
public abstract class LibraryItem {
    final Title title;
    final Person person;
    final Year year;
    private final TypeOfLibraryItem type;

    LibraryItem(Title title, Person person, Year year, TypeOfLibraryItem type) {
        this.title = title;
        this.person = person;
        this.year = year;
        this.type = type;
    }

    boolean isOfType(TypeOfLibraryItem type){
        return this.type == type;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (!(object instanceof LibraryItem)) {
            return false;
        }
        LibraryItem item = (LibraryItem) object;
        return title.equals(item.title) && type.equals(item.type);
    }

    Title getTitle() {
        return title;
    }

    Person getPerson() {
        return person;
    }

    Year getYear() {
        return year;
    }

    abstract List<String> getDetails();
}
