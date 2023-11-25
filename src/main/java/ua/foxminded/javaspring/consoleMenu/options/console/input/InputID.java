package ua.foxminded.javaspring.consoleMenu.options.console.input;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.dao.DAO;

public class InputID<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InputID.class);

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
            LOGGER.info("Invalid selected ID, is not exist.");
            return false;
        }
    }
}
