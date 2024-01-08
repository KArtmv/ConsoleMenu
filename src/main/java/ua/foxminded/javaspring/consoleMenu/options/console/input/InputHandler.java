package ua.foxminded.javaspring.consoleMenu.options.console.input;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.options.console.output.ConsolePrinter;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;
import ua.foxminded.javaspring.consoleMenu.util.ApplicationMessages;

import java.util.InputMismatchException;

public class InputHandler {
    private static final String ALPHABETIC_PATTERN = "\\p{Alpha}+";
    private MyScanner scanner;
    private ConsolePrinter consolePrinter;
    private StudentService studentService;
    private ApplicationMessages messages;

    @Autowired
    public InputHandler(MyScanner scanner, ConsolePrinter consolePrinter, StudentService studentService, ApplicationMessages messages) {
        this.scanner = scanner;
        this.consolePrinter = consolePrinter;
        this.studentService = studentService;
        this.messages = messages;
    }

    public String inputOptionMenu() {
        String s = scanner.getLine();
        if (s.equals("x")) {
            scanner.close();
        }
        return s;
    }

    public Student getDataOfNewStudent(){
        Student student = new Student();
        consolePrinter.print(messages.ADD_NEW_STUDENT);
        consolePrinter.print(messages.INPUT_FIRST_NAME);
        student.setFirstName(getString());
        consolePrinter.print(messages.INPUT_LAST_NAME);
        student.setLastName(getString());
        consolePrinter.print(messages.ADD_GROUP_TO_STUDENT);
        consolePrinter.printAllGroups();
        student.setGroupID(scanner.getLong());

        return student;
    }

    private String getString() {
        String s = scanner.getLine();
        if (s.matches(ALPHABETIC_PATTERN)) {
            return s;
        } else {
            throw new InputMismatchException(String.format("Input: '%s', does not match the alphabetic pattern.", s));
        }
    }

    public boolean verifyValidStudent(Student student) {
        Student selectedStudent = studentService.getStudent(student);
        consolePrinter.print(String.format(messages.VERIFY_STUDENT,
                selectedStudent.getStudentID(), selectedStudent.getFirstName(), selectedStudent.getLastName()));

        return scanner.getLine().equalsIgnoreCase("yes");
    }


}
