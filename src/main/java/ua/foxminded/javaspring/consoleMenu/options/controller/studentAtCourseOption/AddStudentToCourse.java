package ua.foxminded.javaspring.consoleMenu.options.controller.studentAtCourseOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.options.console.input.InputID;
import ua.foxminded.javaspring.consoleMenu.options.console.output.OutputListOfCourses;
import ua.foxminded.javaspring.consoleMenu.service.StudentAtCourseService;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

import java.util.InputMismatchException;

public class AddStudentToCourse {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddStudentToCourse.class);

    private OutputListOfCourses outputListOfCourses;
    private StudentAtCourseService studentAtCourseService;
    private InputID<Course> courseInputID;
    private InputID<Student> studentInputID;
    private StudentService studentService;
    private ConsoleInput consoleInput;

    @Autowired
    public AddStudentToCourse(OutputListOfCourses outputListOfCourses, StudentAtCourseService studentAtCourseService, InputID<Course> courseInputID,
                              InputID<Student> studentInputID, StudentService studentService, ConsoleInput consoleInput) {
        this.outputListOfCourses = outputListOfCourses;
        this.studentAtCourseService = studentAtCourseService;
        this.courseInputID = courseInputID;
        this.studentInputID = studentInputID;
        this.studentService = studentService;
        this.consoleInput = consoleInput;
    }

    public void addStudentToCourse() {
        try {
            Student student = getStudent();
            if (verifyValidStudent(student)) {
                add(new StudentAtCourse(student, getCourse()));
            }
        } catch (InvalidIdException | InputMismatchException e) {
            LOGGER.info("Error adding student to course: " + e.getMessage());
        }
    }

    private Student getStudent() throws InvalidIdException {
        System.out.println("Input student ID.");
        return new Student(studentInputID.inputID());
    }

    private boolean verifyValidStudent(Student student) {
        Student selectedStudent = studentService.getStudentByID(student);
        System.out.printf("Received ID of student which should to add to course: %s %s.\n",
                selectedStudent.getFirstName(), selectedStudent.getLastName());
        System.out.print("Confirm that the student is correct.\nInsert 'yes' to confirm or any other key to cancel.");

        return consoleInput.inputCharacters().equalsIgnoreCase("yes");
    }

    private Course getCourse() throws InvalidIdException {
        outputListOfCourses.viewAllCourses();
        System.out.println("Input course ID, choose from list.");
        return new Course(courseInputID.inputID());
    }

    private void add(StudentAtCourse studentAtCourse) {
        try {
            studentAtCourseService.addStudentToCourse(studentAtCourse);
        } catch (Exception e) {
            LOGGER.error("Failed add student to course: " + e.getMessage());
        }
    }
}
