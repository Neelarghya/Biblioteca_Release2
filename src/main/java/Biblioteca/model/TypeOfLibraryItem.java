package Biblioteca.model;

import Biblioteca.common.Constants;
import Biblioteca.model.value_objects.Title;

import java.util.Arrays;
import java.util.List;

public enum TypeOfLibraryItem {
    BOOK(Constants.NUMBER_OF_COLUMNS_IN_BOOK_DETAILS, "book") {
        @Override
        public LibraryItem createLibraryItemForTitle(Title title) {
            return new Book(title, null, null);
        }

        @Override
        public List<String> getDetailsHeader() {
            return Arrays.asList("Book Person", "Author", "Year of Publish");
        }
    },
    MOVIE(Constants.NUMBER_OF_COLUMNS_IN_MOVIE_DETAILS, "movie") {
        @Override
        public LibraryItem createLibraryItemForTitle(Title title) {
            return new Movie(title, null, null, null);
        }

        @Override
        public List<String> getDetailsHeader() {
            return Arrays.asList("Movie Person", "Director", "Year of Publish", "Rating");
        }
    };

    private final int numberOfFields;
    private final String name;

    TypeOfLibraryItem(int numberOfFields, String name) {
        this.numberOfFields = numberOfFields;
        this.name = name;
    }

    public int getNumberOfFields() {
        return numberOfFields;
    }

    public String getName() {
        return name;
    }

    public abstract LibraryItem createLibraryItemForTitle(Title title);

    public abstract List<String> getDetailsHeader();
}
