package Biblioteca.contoller.commands;

import Biblioteca.common.Constants;
import Biblioteca.model.Library;
import Biblioteca.view.InputDriver;
import Biblioteca.view.OutputDriver;

public class LogoutCommand implements Command {
    @Override
    public void perform(Library library, OutputDriver outputDriver, InputDriver inputDriver) {
        library.logout();
        outputDriver.println(Constants.LOGOUT_MESSAGE);
    }
}
