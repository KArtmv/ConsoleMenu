package ua.foxminded.javaspring.consoleMenu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ConsoleInput;
import ua.foxminded.javaspring.consoleMenu.options.console.output.ConsolePrinter;
import ua.foxminded.javaspring.consoleMenu.service.CourseService;

import java.util.List;

@Controller
public class CourseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);
    private CourseService courseService;
    private ConsoleInput consoleInput;
    private ConsolePrinter consolePrinter;

    @Autowired
    public CourseController(CourseService courseService, ConsoleInput consoleInput, ConsolePrinter consolePrinter) {
        this.courseService = courseService;
        this.consoleInput = consoleInput;
        this.consolePrinter = consolePrinter;
    }

    public void allStudentsFromCourse() {
        try {
            consolePrinter.printAllCourses();
            System.out.println("Choose and input the ID of the course from the list to view all students enrolled in this course.");
            List<StudentAtCourse> studentsFromCourse = courseService.allStudentsFromCourse((new Course((long) consoleInput.inputNumbers())));

            if (!CollectionUtils.isEmpty(studentsFromCourse)) {
                consolePrinter.viewAllStudentsFromCourse(studentsFromCourse);
            } else {
                System.out.println("No found students for the selected course.");
            }
        } catch (InvalidIdException e) {
            LOGGER.info("Failed to found all student studies at course: " + e.getMessage());
        }
    }
}
