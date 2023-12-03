package ua.foxminded.javaspring.consoleMenu.options.controller.studentOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.options.StudentConfirmationHandler;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ItemID;
//import ua.foxminded.javaspring.consoleMenu.service.StudentAtCourseService;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

public class DeleteStudentByID {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteStudentByID.class);

    private ItemID<Student> inputID;
    private StudentService studentService;
    private StudentConfirmationHandler verifyValidStudent;

    @Autowired
    public DeleteStudentByID(ItemID<Student> inputID, StudentService studentService, StudentConfirmationHandler verifyValidStudent) {
        this.inputID = inputID;
        this.studentService = studentService;
        this.verifyValidStudent = verifyValidStudent;
    }

    public void remove() {
        try {
            Student student = getStudentID();
            if (verifyValidStudent.verifyValidStudent(student)) {
                removing(student);
            }
        } catch (InvalidIdException e) {
            LOGGER.info("Error getting student ID: {}", e.getMessage());
        }
    }

    private Student getStudentID() throws InvalidIdException {
        System.out.println("Enter the ID of the student you want to remove.");
        return new Student(inputID.inputID());
    }


    private void removing(Student student) {
        try {
            if (!studentService.allCoursesOfStudent(student).isEmpty()) {
                studentService.removeStudentFromAllTheirCourses(student);
            }
            studentService.deleteStudent(student);
        } catch (Exception e) {
            LOGGER.error("Failed removing student: {}", e.getMessage());
        }
    }
}
