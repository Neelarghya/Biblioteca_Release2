package Biblioteca.contoller.commands;

import Biblioteca.model.Library;
import Biblioteca.model.TypeOfLibraryItem;
import Biblioteca.model.value_objects.Title;
import Biblioteca.view.InputDriver;
import Biblioteca.view.OutputDriver;

// Action to return a book from the Library that was previously checked out
public class ReturnCommand implements Command {
    private final TypeOfLibraryItem type;

    public ReturnCommand(TypeOfLibraryItem type) {
        this.type = type;
    }

    @Override
    public void perform(Library library, OutputDriver outputDriver, InputDriver inputDriver) {
        outputDriver.print("Enter name of the " + type.getName() + " you want to return: ");
        String bookName = inputDriver.getInput();

        if (!library.hasCheckedOut(new Title(bookName), type)) {
            outputDriver.println("That is not a valid " + type.getName() + " to return.");
            return;
        }

        library.returnItem(new Title(bookName), type);
        outputDriver.println("Thank you for returning the " + type.getName() + ".");
    }
}
