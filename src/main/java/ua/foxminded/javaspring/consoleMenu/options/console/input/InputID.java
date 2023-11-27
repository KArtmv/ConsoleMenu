package ua.foxminded.javaspring.consoleMenu.options.console.input;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.dao.DAO;
import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;

public class InputID<T> {

    private ConsoleInput consoleInput;
    private DAO<T> dao;

    @Autowired
    public InputID(ConsoleInput consoleInput, DAO<T> dao) {
        this.consoleInput = consoleInput;
        this.dao = dao;
    }

    public Long inputID() throws InvalidIdException {
        int receivedID = consoleInput.inputNumbers();

        if (dao.isValidItemID(receivedID)) {
            return (long) receivedID;
        } else {
            throw new InvalidIdException(String.format("Invalid selected ID '%s', does not exist. Item: '%s'.", receivedID, dao.getClass()));
        }
    }
}
