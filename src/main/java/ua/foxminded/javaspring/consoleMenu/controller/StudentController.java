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
import ua.foxminded.javaspring.consoleMenu.options.StudentConfirmationHandler;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
//import ua.foxminded.javaspring.consoleMenu.options.console.input.NewStudentData;
import ua.foxminded.javaspring.consoleMenu.options.console.output.ConsolePrinter;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

import java.util.InputMismatchException;
import java.util.List;

@Controller
public class StudentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
    private StudentService studentService;
    private ConsolePrinter consolePrinter;
    private ConsoleInput consoleInput;
    private ConsolePrinter printAllGroups;
    private StudentConfirmationHandler verifyValidStudent;

    @Autowired
    public StudentController(StudentService studentService, ConsolePrinter consolePrinter, ConsoleInput consoleInput, ConsolePrinter printAllGroups, StudentConfirmationHandler verifyValidStudent) {
        this.studentService = studentService;
        this.consolePrinter = consolePrinter;
        this.consoleInput = consoleInput;
        this.printAllGroups = printAllGroups;
        this.verifyValidStudent = verifyValidStudent;
    }

    public void addNewStudent() {
        try {
            System.out.println("Input data of a new student.");
            Student student = new Student();
            System.out.println("Input student first name and press enter.");
            student.setFirstName(consoleInput.inputCharacters());
            System.out.println("Input student last name and press enter.");
            student.setLastName(consoleInput.inputCharacters());
            System.out.println("Now you should choose a group from list to which should add student.\n Input ID and press enter.");
            printAllGroups.printAllGroups();
            student.setGroupID((long) consoleInput.inputNumbers());

            if (studentService.addNewStudent(student)) {
                System.out.println("Success, student had been added");
            }
        } catch (InputMismatchException | InvalidIdException e) {
            LOGGER.info("Failed add student: {}.", e.getMessage());
        }
    }

    public void deleteStudent() {
        try {
            System.out.println("Enter the ID of the student you want to remove.");
            Student student = new Student((long) consoleInput.inputNumbers());
            if (verifyValidStudent.verifyValidStudent(student) && studentService.deleteStudent(student)) {
                System.out.println("Success, student has been removed!");
            }
        } catch (InvalidIdException | InputMismatchException e) {
            LOGGER.info("Failed to remove student: {}", e.getMessage());
        }
    }

    public void addStudentToCourse() {
        try {
            System.out.println("Input student ID which should be add to course.");
            Student student = new Student((long) consoleInput.inputNumbers());

            if (verifyValidStudent.verifyValidStudent(student)) {
                System.out.println("Input course ID, choose from list.");
                consolePrinter.printAllCourses();
                if (studentService.addStudentToCourse(new StudentAtCourse(student, new Course((long) consoleInput.inputNumbers())))) {
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
            Student student = studentService.getStudent(new Student((long) consoleInput.inputNumbers()));
            List<StudentAtCourse> allStudentCourses = studentService.getAllCoursesOfStudent(student);

            if (!CollectionUtils.isEmpty(allStudentCourses) && verifyValidStudent.verifyValidStudent(student)) {
                System.out.println("Choose enrollment ID from the list to wish remove and press enter.");
                consolePrinter.viewAllCoursesOfStudent(allStudentCourses);
                if (studentService.removeStudentFromCourse(new StudentAtCourse((long) consoleInput.inputNumbers(), student))) {
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
