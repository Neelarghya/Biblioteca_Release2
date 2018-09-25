package Biblioteca.contoller;

import Biblioteca.contoller.commands.*;
import Biblioteca.model.Customer;
import Biblioteca.model.Library;
import Biblioteca.view.InputDriver;
import Biblioteca.view.OutputDriver;

import static Biblioteca.contoller.MenuVisibility.*;
import static Biblioteca.model.TypeOfLibraryItem.BOOK;
import static Biblioteca.model.TypeOfLibraryItem.MOVIE;

// Type of action user wants to perform
public enum Menu {
    QUIT("Quit", new QuitCommand(), ALWAYS),
    LIST_BOOKS("List Books", new ListCommand(BOOK), ALWAYS),
    LIST_MOVIES("List Movies", new ListCommand(MOVIE), ALWAYS),
    CHECK_OUT_BOOK("Check Out a book", new CheckOutCommand(BOOK), LOGGED_IN),
    CHECK_OUT_MOVIE("Check Out a movie", new CheckOutCommand(MOVIE), LOGGED_IN),
    RETURN_BOOK("Return a book", new ReturnCommand(BOOK), LOGGED_IN),
    RETURN_MOVIE("Return a movie", new ReturnCommand(MOVIE), LOGGED_IN),
    DISPLAY_USER_INFORMATION("Display User Information", new DisplayUserDetailsCommand(), LOGGED_IN),
    LOGIN("Login", new LoginCommand(), LOGGED_OUT),
    LOGOUT("Logout", new LogoutCommand(), LOGGED_IN),
    INVALID_OPTION("", new InvalidOptionCommand(), NEVER);

    private final String display;
    private final Command command;
    private final MenuVisibility menuVisibility;

    Menu(String display, Command command, MenuVisibility menuVisibility) {
        this.display =   " to " + display;
        this.command = command;
        this.menuVisibility = menuVisibility;
    }

    public void perform(Library library, OutputDriver outputDriver, InputDriver inputDriver) {
        command.perform(library, outputDriver, inputDriver);
    }

    public String getMenu(int option) {
        return "Enter " + option + display;
    }

    public boolean isDisplayable(Customer customer) {
        return menuVisibility.isDisplayable(customer);
    }
}
