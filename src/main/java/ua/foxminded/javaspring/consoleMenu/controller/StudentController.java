package ua.foxminded.javaspring.consoleMenu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.console.input.InputHandler;
import ua.foxminded.javaspring.consoleMenu.options.console.input.MyScanner;
import ua.foxminded.javaspring.consoleMenu.options.console.output.ConsolePrinter;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;
import ua.foxminded.javaspring.consoleMenu.util.ApplicationMessages;

import java.util.InputMismatchException;
import java.util.List;

@Controller
public class StudentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
    private StudentService studentService;
    private ConsolePrinter consolePrinter;
    private InputHandler inputHandler;
    private ApplicationMessages messages;

    @Autowired
    public StudentController(StudentService studentService, ConsolePrinter consolePrinter, InputHandler inputHandler, ApplicationMessages messages) {
        this.studentService = studentService;
        this.consolePrinter = consolePrinter;
        this.inputHandler = inputHandler;
        this.messages = messages;
    }

    public void addNewStudent() {
        try {
            if (studentService.addNewStudent(inputHandler.getDataOfNewStudent())) {
                consolePrinter.print(messages.STUDENT_ADDED);
            }
        } catch (InputMismatchException | InvalidIdException e) {
            LOGGER.info("Failed add student: {}.", e.getMessage());
        }
    }

    public void deleteStudent() {
        try {
            consolePrinter.print(messages.REMOVE_STUDENT_BY_ID);
            Student student = inputHandler.getStudent();
            if (inputHandler.verifyValidStudent(student) && studentService.deleteStudent(student)) {
                consolePrinter.print(messages.STUDENT_REMOVED);
            }
        } catch (InvalidIdException | InputMismatchException e) {
            LOGGER.info("Failed to remove student: {}", e.getMessage());
        }
    }

    public void addStudentToCourse() {
        try {
            consolePrinter.print(messages.ADD_STUDENT_TO_COURSE);
            Student student = inputHandler.getStudent();

            if (inputHandler.verifyValidStudent(student)) {
                consolePrinter.print(messages.ENTER_COURSE_ID);
                consolePrinter.printAllCourses();
                if (studentService.addStudentToCourse(new StudentAtCourse(student, inputHandler.getCourse()))) {
                    consolePrinter.print(messages.STUDENT_ADDED_TO_COURSE);
                }
            }
        } catch (InvalidIdException | InputMismatchException e) {
            LOGGER.info("Error adding student to course: {}.", e.getMessage());
        }
    }

    public void removeStudentFromCourse() {
        try {
            consolePrinter.print(messages.REMOVE_STUDENT_FROM_COURSE);
            Student student = studentService.getStudent(inputHandler.getStudent());
            List<StudentAtCourse> allStudentCourses = studentService.getAllCoursesOfStudent(student);

            if (!CollectionUtils.isEmpty(allStudentCourses) && inputHandler.verifyValidStudent(student)) {
                consolePrinter.print(messages.CHOOSE_ENROLLMENT_ID);
                consolePrinter.viewAllCoursesOfStudent(allStudentCourses);
                StudentAtCourse enrollmentID = inputHandler.getEnrollment();
                enrollmentID.setStudent(student);

                if (studentService.removeStudentFromCourse(enrollmentID)) {
                    consolePrinter.print(String.format(messages.STUDENT_REMOVED_FROM_COURSE,
                            student.getStudentID(), student.getFirstName(), student.getLastName(), allStudentCourses.get(0).getCourse().getCourseName()));
                }
            } else {
                consolePrinter.print(String.format(messages.STUDENT_HAS_NOT_COURSE , student.getFirstName(), student.getLastName()));
            }
        } catch (InvalidIdException | InputMismatchException e) {
            LOGGER.info("Removing student from course is failed: {}", e.getMessage());
        }
    }
}
