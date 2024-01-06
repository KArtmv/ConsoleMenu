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

import java.util.InputMismatchException;
import java.util.List;

@Controller
public class StudentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
    private StudentService studentService;
    private ConsolePrinter consolePrinter;
    private MyScanner scanner;
    private InputHandler input;

    @Autowired
    public StudentController(StudentService studentService, ConsolePrinter consolePrinter, MyScanner scanner, InputHandler input) {
        this.studentService = studentService;
        this.consolePrinter = consolePrinter;
        this.scanner = scanner;
        this.input = input;
    }

    public void addNewStudent() {
        try {
            if (studentService.addNewStudent(input.getStudent())) {
                System.out.println("Success, student had been added");
            }
        } catch (InputMismatchException | InvalidIdException e) {
            LOGGER.info("Failed add student: {}.", e.getMessage());
        }
    }

    public void deleteStudent() {
        try {
            System.out.println("Enter the ID of the student you want to remove.");
            Student student = new Student(scanner.getLong());
            if (input.verifyValidStudent(student) && studentService.deleteStudent(student)) {
                System.out.println("Success, student has been removed!");
            }
        } catch (InvalidIdException | InputMismatchException e) {
            LOGGER.info("Failed to remove student: {}", e.getMessage());
        }
    }

    public void addStudentToCourse() {
        try {
            System.out.println("Input student ID which should be add to course.");
            Student student = new Student(scanner.getLong());

            if (input.verifyValidStudent(student)) {
                System.out.println("Input course ID, choose from list.");
                consolePrinter.printAllCourses();
                if (studentService.addStudentToCourse(new StudentAtCourse(student, new Course(scanner.getLong())))) {
                    System.out.println("Success student has been added to course!");
                }
            }
        } catch (InvalidIdException | InputMismatchException e) {
            LOGGER.info("Error adding student to course: {}.", e.getMessage());
        }
    }

    public void removeStudentFromCourse() {
        try {
            System.out.println("Input the ID of student which should be remove from course. Then press enter.");
            Student student = studentService.getStudent(new Student(scanner.getLong()));
            List<StudentAtCourse> allStudentCourses = studentService.getAllCoursesOfStudent(student);

            if (!CollectionUtils.isEmpty(allStudentCourses) && input.verifyValidStudent(student)) {
                System.out.println("Choose enrollment ID from the list to wish remove and press enter.");
                consolePrinter.viewAllCoursesOfStudent(allStudentCourses);
                if (studentService.removeStudentFromCourse(new StudentAtCourse(scanner.getLong(), student))) {
                    System.out.printf("Success, student: ID %s, %s %s, has been removed from course %s!\n",
                            student.getStudentID(), student.getFirstName(), student.getLastName(), allStudentCourses.get(0).getCourse().getCourseName());
                }
            } else {
                System.out.printf("The student: %s %s have not relate to any course.\n", student.getFirstName(), student.getLastName());
            }
        } catch (InvalidIdException | InputMismatchException e) {
            LOGGER.info("Removing student from course is failed: {}", e.getMessage());
        }
    }
}
