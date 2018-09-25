package Biblioteca.contoller.commands;

import Biblioteca.model.Library;
import Biblioteca.model.TypeOfLibraryItem;
import Biblioteca.model.value_objects.Title;
import Biblioteca.view.InputDriver;
import Biblioteca.view.OutputDriver;

// Action to check out a book from the Library
public class CheckOutCommand implements Command {
    private final TypeOfLibraryItem type;

    public CheckOutCommand(TypeOfLibraryItem type) {
        this.type = type;
    }

    @Override
    public void perform(Library library, OutputDriver outputDriver, InputDriver inputDriver) {
        outputDriver.print("Enter name of the " + type.getName() + " you want to check out: ");
        String name = inputDriver.getInput();

        if (!library.contains(new Title(name), type)) {
            outputDriver.println("That " + type.getName() + " is not available.");
            return;
        }

        library.checkOut(new Title(name), type);
        outputDriver.println("Thank you! Enjoy the " + type.getName());
    }
}
