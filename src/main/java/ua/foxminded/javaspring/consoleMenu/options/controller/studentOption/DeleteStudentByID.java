package ua.foxminded.javaspring.consoleMenu.options.controller.studentOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
        Student student = new Student(getStudentID());

        if (isStudentCorrect(student)) {
            removing(student);
        } else {
            LOGGER.info("Removal canceled.");
        }
    }

    private Long getStudentID() {
        System.out.println("Enter the ID of the student you want to remove.");
        return inputID.inputID();
    }

    private boolean isStudentCorrect(Student student) {
        Student selectedStudent = studentService.getStudentByID(student);
        System.out.printf("Received ID of student which should to remove: %s %s.\n",
                selectedStudent.getFirstName(), selectedStudent.getLastName());
        System.out.print("Confirm removal (yes/ or anything if no): ");

        return consoleInput.inputCharacters().equalsIgnoreCase("yes");
    }

    private void removing(Student student) {
        if (!studentService.allCoursesOfStudent(student).isEmpty()) {
            enrollmentService.removeStudentFromAllTheirCourses(student);
        }
        tryDeleteStudent(student);
    }

    private void tryDeleteStudent(Student student) {
        if (studentService.deleteStudent(student)) {
            System.out.println("Success, the student had been removed.");
        } else {
            LOGGER.error("Failed to remove the student.");
        }
    }
}
