package Biblioteca;

import Biblioteca.contoller.LibraryManagementSystem;
import Biblioteca.model.*;
import Biblioteca.model.value_objects.*;
import Biblioteca.view.InputDriver;
import Biblioteca.view.OutputDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BibliotecaApplication {
    public static void main(String[] args) {
        List<LibraryItem> libraryItems = new ArrayList<>();
        libraryItems.add(new Book(new Title("Book1"), new Person("Mrinal Kanti Ghosh"), new Year(1996)));
        libraryItems.add(new Book(new Title("Book2"), new Person("Arpan"), new Year(1997)));
        libraryItems.add(new Book(new Title("Book3"), new Person("Sayan"), new Year(1995)));
        libraryItems.add(new Book(new Title("Book4"), new Person("Bari"), new Year(1994)));
        libraryItems.add(new Movie(new Title("Movie1"), new Person("Sridhar"), new Year(1896), new Rating(6)));
        libraryItems.add(new Movie(new Title("Movie2"), new Person("Bari"), new Year(1897), new Rating(11)));
        libraryItems.add(new Movie(new Title("Movie3"), new Person("Swarnava"), new Year(1947), new Rating(10)));

        List<Customer> customers = Arrays.asList(
                new Customer(new LibraryNumber("1"), new Password("1"), new Person("Bari"), new EmailAddress("bari@gmail.com"), new PhoneNumber(1234890)),
                new Customer(new LibraryNumber("2"), new Password("2"), new Person("Neel"), new EmailAddress("Neel@gmail.com"), new PhoneNumber(34567890)),
                new Customer(new LibraryNumber("3"), new Password("3"), new Person("Nikhil"), new EmailAddress("nikhil@gmail.com"), new PhoneNumber(123456))
        );

        Library library = new Library(libraryItems, customers);
        OutputDriver outputDriver = new OutputDriver();
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem(library, outputDriver, new InputDriver());

        libraryManagementSystem.start();
    }
}
