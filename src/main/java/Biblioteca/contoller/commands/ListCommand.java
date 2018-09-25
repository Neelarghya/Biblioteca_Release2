package Biblioteca.contoller.commands;

import Biblioteca.model.Library;
import Biblioteca.model.TypeOfLibraryItem;
import Biblioteca.view.InputDriver;
import Biblioteca.view.OutputDriver;

// Action to display all the available books in the library
public class ListCommand implements Command {
    private final TypeOfLibraryItem type;

    public ListCommand(TypeOfLibraryItem type) {
        this.type = type;
    }

    @Override
    public void perform(Library library, OutputDriver outputDriver, InputDriver inputDriver) {
        outputDriver.printInColumns(type.getDetailsHeader(), type.getNumberOfFields());
        outputDriver.printInColumns(library.getItemDetails(type), type.getNumberOfFields());
    }
}
