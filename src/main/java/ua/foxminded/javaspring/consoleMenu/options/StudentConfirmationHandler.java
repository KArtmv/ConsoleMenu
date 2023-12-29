package ua.foxminded.javaspring.consoleMenu.options;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.options.console.input.MyScanner;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

public class StudentConfirmationHandler {

    private StudentService studentService;
    private MyScanner scanner;

    @Autowired
    public StudentConfirmationHandler(StudentService studentService, MyScanner scanner) {
        this.studentService = studentService;
        this.scanner = scanner;
    }

    public boolean verifyValidStudent(Student student) {
        Student selectedStudent = studentService.getStudent(student);
        System.out.printf("Confirm that the student: student: ID %s, %s %s is correct.\nInsert 'yes' to confirm or any other key to cancel: ",
                selectedStudent.getStudentID(), selectedStudent.getFirstName(), selectedStudent.getLastName());

        return scanner.getLine().equalsIgnoreCase("yes");
    }
}
