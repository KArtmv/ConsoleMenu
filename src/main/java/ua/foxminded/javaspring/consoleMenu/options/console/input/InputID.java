package ua.foxminded.javaspring.consoleMenu.options.console.input;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.dao.DAO;

public class InputID<T> {

    private ConsoleInput consoleInput;
    private DAO<T> dao;

    @Autowired
    public InputID(ConsoleInput consoleInput, DAO<T> dao) {
        this.consoleInput = consoleInput;
        this.dao = dao;
    }

    public Long inputID() {
        int receivedID;
        do {
            receivedID = consoleInput.inputNumbers();
        } while (!isValidID(receivedID));
        return (long) receivedID;
    }

    private boolean isValidID(Integer receivedID) {
        if (dao.isValidItemID(receivedID)) {
            return true;
        } else {
            System.out.println("Invalid selected ID, is not exist. Please try again.");
            return false;
        }
    }
}
