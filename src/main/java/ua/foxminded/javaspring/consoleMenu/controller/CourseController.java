package ua.foxminded.javaspring.consoleMenu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.console.input.MyScanner;
import ua.foxminded.javaspring.consoleMenu.options.console.output.ConsolePrinter;
import ua.foxminded.javaspring.consoleMenu.service.CourseService;
import ua.foxminded.javaspring.consoleMenu.util.ApplicationMessages;

import java.util.InputMismatchException;
import java.util.List;

@Controller
public class CourseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);
    private CourseService courseService;
    private MyScanner scanner;
    private ConsolePrinter consolePrinter;
    private ApplicationMessages messages;

    @Autowired
    public CourseController(CourseService courseService, MyScanner scanner, ConsolePrinter consolePrinter, ApplicationMessages messages) {
        this.courseService = courseService;
        this.scanner = scanner;
        this.consolePrinter = consolePrinter;
        this.messages = messages;
    }

    public void allStudentsFromCourse() {
        LOGGER.info("Run allStudentsFromCourse.");
        try {
            consolePrinter.printAllCourses();
            consolePrinter.print(messages.STUDENTS_COURSES);
            Course course = new Course();
            course.setCourseID(scanner.getLong());
            LOGGER.info("Received course ID: {}", course.getCourseID());
            List<StudentAtCourse> studentsFromCourse = courseService.allStudentsFromCourse(course);

            if (!CollectionUtils.isEmpty(studentsFromCourse)) {
                LOGGER.debug("The list of students on the course has been successfully compiled");
                consolePrinter.viewAllStudentsFromCourse(studentsFromCourse);
            } else {
                LOGGER.debug("No found students for the selected course.");
            }
        } catch (InvalidIdException | InputMismatchException e) {
            LOGGER.error("Failed to found the students: {}", e.getMessage());
        }
    }
}
