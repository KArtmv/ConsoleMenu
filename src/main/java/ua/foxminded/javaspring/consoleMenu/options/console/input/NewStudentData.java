package ua.foxminded.javaspring.consoleMenu.options.console.input;

import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.options.print.ItemPrint;

public class NewStudentData {

    private ConsoleInput consoleInput;
    private ItemID<Group> itemID;
    private ItemPrint<Group> printAllGroups;

    public NewStudentData(ConsoleInput consoleInput, ItemID<Group> itemID, ItemPrint<Group> printAllGroups) {
        this.consoleInput = consoleInput;
        this.itemID = itemID;
        this.printAllGroups = printAllGroups;
    }
    
    public Student get(){
        return new Student(getFirstName(), getLastName(), getGroupID());
    }

    private String getFirstName() {
        System.out.println("Input student first name and press enter.");
        return consoleInput.inputCharacters();
    }

    private String getLastName() {
        System.out.println("Input student last name and press enter.");
        return consoleInput.inputCharacters();
    }

    private Long getGroupID() throws InvalidIdException {
        System.out.println("Now you should choose a group from list to which should add student.\n Input ID and press enter.");
        printAllGroups.printAllAvailableItems();
        return itemID.inputID();
    }
}
