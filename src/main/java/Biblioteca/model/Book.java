package Biblioteca.model;

import Biblioteca.model.value_objects.Person;
import Biblioteca.model.value_objects.Title;
import Biblioteca.model.value_objects.Year;

import java.util.ArrayList;
import java.util.List;

// A Document made up of pages
public class Book extends LibraryItem {
    public Book(Title title) {
        super(title, null, null, TypeOfLibraryItem.MOVIE);
    }

    public Book(Title title, Person person, Year year) {
        super(title, person, year, TypeOfLibraryItem.BOOK);
    }

    @Override
    public String toString() {
        return title.getValue() + "\t" + person.getName() + "\t" + year.getValue();
    }

    @Override
    List<String> getDetails() {
        List<String> output = new ArrayList<>();
        output.add(getTitle().toString());
        output.add(getPerson().toString());
        output.add(getYear().toString());

        return output;
    }
}
