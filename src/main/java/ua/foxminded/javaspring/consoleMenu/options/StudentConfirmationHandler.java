package ua.foxminded.javaspring.consoleMenu.options;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

public class StudentConfirmationHandler {

    private StudentService studentService;
    private ConsoleInput consoleInput;

    @Autowired
    public StudentConfirmationHandler(StudentService studentService, ConsoleInput consoleInput) {
        this.studentService = studentService;
        this.consoleInput = consoleInput;
    }

    public boolean verifyValidStudent(Student student) {
            Student selectedStudent = studentService.getStudent(student);
            System.out.printf("Confirm that the student: student: ID %s, %s %s is correct.\nInsert 'yes' to confirm or any other key to cancel: ",
                     selectedStudent.getStudentID(), selectedStudent.getFirstName(), selectedStudent.getLastName());

        return consoleInput.inputCharacters().equalsIgnoreCase("yes");
    }
}
