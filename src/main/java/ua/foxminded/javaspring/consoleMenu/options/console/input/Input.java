package ua.foxminded.javaspring.consoleMenu.options.console.input;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.options.console.output.ConsolePrinter;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

import java.util.InputMismatchException;

public class Input {
    private static final String ALPHABETIC_PATTERN = "\\p{Alpha}+";
    private MyScanner scanner;
    private ConsolePrinter consolePrinter;
    private StudentService studentService;

    @Autowired
    public Input(MyScanner scanner, ConsolePrinter consolePrinter, StudentService studentService) {
        this.scanner = scanner;
        this.consolePrinter = consolePrinter;
        this.studentService = studentService;
    }

    public String inputOptionMenu() {
        String s = scanner.getLine();
        if (s.equals("x")) {
            scanner.close();
        }
        return s;
    }

    public Student getStudent(){
        Student student = new Student();
        consolePrinter.print("Input data of a new student.");
        consolePrinter.print("Input student first name and press enter.");
        student.setFirstName(getString());
        consolePrinter.print("Input student last name and press enter.");
        student.setLastName(getString());
        consolePrinter.print("Now you should choose a group from list to which should add student.\n Input ID and press enter.");
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
        consolePrinter.print(String.format("Confirm that the student: student: ID %s, %s %s is correct.\nInsert 'yes' to confirm or any other key to cancel: ",
                selectedStudent.getStudentID(), selectedStudent.getFirstName(), selectedStudent.getLastName()));

        return scanner.getLine().equalsIgnoreCase("yes");
    }
}
