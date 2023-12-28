package ua.foxminded.javaspring.consoleMenu.options.console.input;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.options.console.output.ConsolePrinter;

import java.util.InputMismatchException;

public class Input {
    private static final String ALPHABETIC_PATTERN = "\\p{Alpha}+";
    private MyScanner consoleInput;
    private ConsolePrinter printAllGroups;

    @Autowired
    public Input(MyScanner consoleInput, ConsolePrinter printAllGroups) {
        this.consoleInput = consoleInput;
        this.printAllGroups = printAllGroups;
    }

    public String inputOptionMenu() {
        String s = consoleInput.getLine();
        if (s.equals("x")) {
            consoleInput.close();
        }
        return s;
    }

    public Student getStudent(){
        System.out.println("Input data of a new student.");
        Student student = new Student();
        System.out.println("Input student first name and press enter.");
        student.setFirstName(getString());
        System.out.println("Input student last name and press enter.");
        student.setLastName(getString());
        System.out.println("Now you should choose a group from list to which should add student.\n Input ID and press enter.");
        printAllGroups.printAllGroups();
        student.setGroupID(consoleInput.getLong());

        return student;
    }

    private String getString() {
        String s = consoleInput.getLine();
        if (s.matches(ALPHABETIC_PATTERN)) {
            return s;
        } else {
            throw new InputMismatchException(String.format("Input: '%s', does not match the alphabetic pattern.", s));
        }
    }
}
