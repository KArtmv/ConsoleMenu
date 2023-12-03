package ua.foxminded.javaspring.consoleMenu.options.controller.studentAtCourseOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.StudentConfirmationHandler;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ItemID;
import ua.foxminded.javaspring.consoleMenu.options.print.ItemPrint;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

import java.util.InputMismatchException;

public class AddStudentToCourse {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddStudentToCourse.class);

    private ItemPrint<Course> printAllCourses;
    private StudentService studentService;
    private ItemID<Course> courseInputID;
    private ItemID<Student> studentInputID;
    private StudentConfirmationHandler verifyValidStudent;

    @Autowired
    public AddStudentToCourse(ItemPrint<Course> printAllCourses, StudentService studentService, ItemID<Course> courseInputID,
                              ItemID<Student> studentInputID, StudentConfirmationHandler verifyValidStudent) {
        this.printAllCourses = printAllCourses;
        this.studentService = studentService;
        this.courseInputID = courseInputID;
        this.studentInputID = studentInputID;
        this.verifyValidStudent = verifyValidStudent;
    }

    public void addStudentToCourse() {
        try {
            Student student = getStudent();
            if (verifyValidStudent.verifyValidStudent(student)) {
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


    private Course getCourse() throws InvalidIdException {
        printAllCourses.printAllAvailableItems();
        System.out.println("Input course ID, choose from list.");
        return new Course(courseInputID.inputID());
    }

    private void add(StudentAtCourse studentAtCourse) {
        try {
            studentService.addStudentToCourse(studentAtCourse);
        } catch (Exception e) {
            LOGGER.error("Failed add student to course: " + e.getMessage());
        }
    }
}
