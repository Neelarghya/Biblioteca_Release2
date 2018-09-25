package Biblioteca.contoller.commands;

import Biblioteca.common.Constants;
import Biblioteca.model.Library;
import Biblioteca.view.InputDriver;
import Biblioteca.view.OutputDriver;

import java.util.Arrays;

public class DisplayUserDetailsCommand implements Command {
    @Override
    public void perform(Library library, OutputDriver outputDriver, InputDriver inputDriver) {
        outputDriver.printInColumns(Arrays.asList("User Name", "Email Address", "Phone Number"),
                Constants.NUMBER_OF_COLUMNS_IN_CUSTOMER_DETAILS);
        outputDriver.printInColumns(library.getCurrentUser().getDetails(),
                Constants.NUMBER_OF_COLUMNS_IN_CUSTOMER_DETAILS);
    }
}
