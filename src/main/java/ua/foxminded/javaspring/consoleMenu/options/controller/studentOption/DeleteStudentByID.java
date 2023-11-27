package ua.foxminded.javaspring.consoleMenu.options.controller.studentOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.exception.DatabaseInteractionException;
import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.options.console.input.InputID;
import ua.foxminded.javaspring.consoleMenu.service.StudentAtCourseService;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

public class DeleteStudentByID {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteStudentByID.class);

    private InputID<Student> inputID;
    private StudentService studentService;
    private StudentAtCourseService enrollmentService;
    private ConsoleInput consoleInput;

    @Autowired
    public DeleteStudentByID(InputID<Student> inputID, StudentService studentService, StudentAtCourseService enrollmentService, ConsoleInput consoleInput) {
        this.inputID = inputID;
        this.studentService = studentService;
        this.enrollmentService = enrollmentService;
        this.consoleInput = consoleInput;
    }

    public void remove() {
        try {
            Student student = getStudentID();
            if (isStudentCorrect(student)) {
                removing(student);
            }
        } catch (InvalidIdException e) {
            LOGGER.info("Error getting student ID: " + e.getMessage());
        }
    }

    private Student getStudentID() throws InvalidIdException {
        System.out.println("Enter the ID of the student you want to remove.");
        return new Student(inputID.inputID());
    }

    private boolean isStudentCorrect(Student student) {
        Student selectedStudent = studentService.getStudentByID(student);
        System.out.printf("Received ID of student which should to remove: %s %s.\n",
                selectedStudent.getFirstName(), selectedStudent.getLastName());
        System.out.print("Confirm that the student is correct.\nInsert 'yes' to confirm or any other key to cancel.");

        return consoleInput.inputCharacters().equalsIgnoreCase("yes");
    }

    private void removing(Student student){
        try {
            if (!studentService.allCoursesOfStudent(student).isEmpty()) {
                enrollmentService.removeStudentFromAllTheirCourses(student);
            }
            studentService.deleteStudent(student);
        } catch (Exception e) {
            LOGGER.error("Failed removing student: " + e.getMessage());
        }
    }
}
