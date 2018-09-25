package Biblioteca.contoller;

import Biblioteca.common.Constants;
import Biblioteca.model.Library;
import Biblioteca.view.InputDriver;
import Biblioteca.view.OutputDriver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//System to manage a library
public class LibraryManagementSystem {
    private final OutputDriver outputDriver;
    private final InputDriver inputDriver;
    private final Library library;

    public LibraryManagementSystem(Library library, OutputDriver outputDriver, InputDriver inputDriver) {
        this.outputDriver = outputDriver;
        this.library = library;
        this.inputDriver = inputDriver;
    }

    public void start() {
        outputDriver.println(Constants.WELCOME_CUSTOMER_MESSAGE);
        runMenu();
    }

    private List<Menu> getValidMenus() {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.isDisplayable(library.getCurrentUser()))
                .collect(Collectors.toList());
    }

    private void runMenu() {
        Menu menu;
        do {
            displayMenu();
            outputDriver.print("Enter your Choice: ");
            final int option = inputDriver.getMenuInput();

            if (!isValidOption(option)) {
                menu = Menu.INVALID_OPTION;
            } else {
                menu = getValidMenus().get(option);
            }

            menu.perform(library, outputDriver, inputDriver);
            outputDriver.printDivider();
        } while (menu != Menu.QUIT);
    }

    private boolean isValidOption(final int option) {
        return option >= 0 && option < getValidMenus().size();
    }

    private void displayMenu() {
        for (int index = 0; index < getValidMenus().size(); index++) {
            Menu menu = getValidMenus().get(index);
            if (menu != Menu.QUIT) {
                outputDriver.println(menu.getMenu(index));
            }
        }

        outputDriver.println(Menu.QUIT.getMenu(0));
    }
}
