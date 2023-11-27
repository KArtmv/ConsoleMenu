package ua.foxminded.javaspring.consoleMenu.options.controller.studentOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.options.console.input.InputID;
import ua.foxminded.javaspring.consoleMenu.options.console.output.OutputListOfGroup;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

import java.util.InputMismatchException;

public class AddNewStudent {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddNewStudent.class);

    private InputID<Group> inputID;
    private ConsoleInput consoleInput;
    private StudentService studentService;
    private OutputListOfGroup outputListOfGroup;

    @Autowired
    public AddNewStudent(InputID<Group> inputID, ConsoleInput consoleInput, StudentService studentService, OutputListOfGroup outputListOfGroup) {
        this.inputID = inputID;
        this.consoleInput = consoleInput;
        this.studentService = studentService;
        this.outputListOfGroup = outputListOfGroup;
    }

    public void add() {
        try {
            System.out.println("Input data of a student.");
            Student student = new Student(getFirstName(), getLastName(), getGroup());
            adding(student);
        } catch (InputMismatchException | InvalidIdException e) {
            LOGGER.info("Failed getting student data:" + e.getMessage());
        }
    }

    private String getFirstName() {
        System.out.println("Input student first name and press enter.");
        return consoleInput.inputCharacters();
    }

    private String getLastName() {
        System.out.println("Input student last name and press enter.");
        return consoleInput.inputCharacters();
    }

    private Long getGroup() throws InvalidIdException {
        System.out.println("Now you should choose a group from list to which should add student.\n Input ID and press enter.");
        outputListOfGroup.viewAllGroups();
        return inputID.inputID();
    }

    private void adding(Student student) {
        try {
            studentService.saveStudent(student);
            System.out.println("Success, student had been added");
        } catch (Exception e) {
            LOGGER.error("Failed to add new student: " + e.getMessage());
        }
    }
}
