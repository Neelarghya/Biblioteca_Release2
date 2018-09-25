package Biblioteca.model;

import Biblioteca.model.value_objects.Person;
import Biblioteca.model.value_objects.Rating;
import Biblioteca.model.value_objects.Title;
import Biblioteca.model.value_objects.Year;

import java.util.ArrayList;
import java.util.List;

// A Motion picture
public class Movie extends LibraryItem{
    private final Rating rating;

    public Movie(Title title, Person person, Year year, Rating rating) {
        super(title, person, year, TypeOfLibraryItem.MOVIE);
        this.rating = rating;
    }

    @Override
    public String toString() {
        return title.getValue() + "\t" + person.getName() + "\t" + year.getValue() + "\t" + rating.displayRating();
    }

    @Override
    List<String> getDetails() {
        List<String> output = new ArrayList<>();
        output.add(getTitle().toString());
        output.add(getPerson().toString());
        output.add(getYear().toString());
        output.add(rating.displayRating());

        return output;
    }
}
