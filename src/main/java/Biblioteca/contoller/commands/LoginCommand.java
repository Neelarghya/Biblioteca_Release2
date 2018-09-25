package Biblioteca.contoller.commands;

import Biblioteca.common.Constants;
import Biblioteca.model.Library;
import Biblioteca.model.value_objects.LibraryNumber;
import Biblioteca.model.value_objects.Password;
import Biblioteca.view.InputDriver;
import Biblioteca.view.OutputDriver;

public class LoginCommand implements Command {
    @Override
    public void perform(Library library, OutputDriver outputDriver, InputDriver inputDriver) {
        outputDriver.println(Constants.ASK_TO_LOGIN);
        outputDriver.print(Constants.ASK_LIBRARY_NUMBER);
        LibraryNumber libraryNumber = new LibraryNumber(inputDriver.getInput());
        outputDriver.print(Constants.ASK_PASSWORD);
        Password password = new Password(inputDriver.getInput());

        if (library.login(libraryNumber, password)) {
            outputDriver.println(Constants.SUCCESSFUL_LOGIN);
        } else {
            outputDriver.println(Constants.UNSUCCESSFUL_LOGIN);
        }
    }
}
