package ua.foxminded.javaspring.consoleMenu.options;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.dao.StudentDAO;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;

import java.util.Optional;

@Component
public class StudentConfirmationHandler {

    private StudentDAO studentDAO;
    private ConsoleInput consoleInput;

    @Autowired
    public StudentConfirmationHandler(StudentDAO studentDAO, ConsoleInput consoleInput) {
        this.studentDAO = studentDAO;
        this.consoleInput = consoleInput;
    }

    public boolean verifyValidStudent(Student student) {
        Optional<Student> selectedStudent = studentDAO.getByItemID(student);

        if (selectedStudent.isPresent()) {
            System.out.printf("Received ID of student which should be removed: %s %s.\n",
                    selectedStudent.get().getFirstName(), selectedStudent.get().getLastName());
            System.out.print("Confirm that the student is correct.\nInsert 'yes' to confirm or any other key to cancel: ");
        }
        return consoleInput.inputCharacters().equalsIgnoreCase("yes");
    }
}
