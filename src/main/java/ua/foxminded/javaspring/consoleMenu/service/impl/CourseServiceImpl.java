package ua.foxminded.javaspring.consoleMenu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ItemID;
import ua.foxminded.javaspring.consoleMenu.options.console.output.ConsolePrinter;
import ua.foxminded.javaspring.consoleMenu.options.print.ItemPrint;
import ua.foxminded.javaspring.consoleMenu.service.CourseService;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);
    private StudentAtCourseDAO studentAtCourseDAO;
    private ConsolePrinter consolePrinter;
    private ItemID<Course> inputID;
    private ItemPrint<Course> print;

    @Autowired
    public CourseServiceImpl(StudentAtCourseDAO studentAtCourseDAO, ConsolePrinter consolePrinter, ItemID<Course> inputID,
                             ItemPrint<Course> print) {
        this.studentAtCourseDAO = studentAtCourseDAO;
        this.consolePrinter = consolePrinter;
        this.inputID = inputID;
        this.print = print;
    }

    @Override
    public void allStudentsFromCourse() {
        try {
            print.printAllAvailableItems();
            System.out.println("Choose and input the ID of the course from the list to view all students enrolled in this course.");
            List<StudentAtCourse> studentsFromCourse = studentAtCourseDAO.allStudentsFromCourse((new Course(inputID.inputID())));

            if (!CollectionUtils.isEmpty(studentsFromCourse)) {
                consolePrinter.viewAllStudentsFromCourse(studentsFromCourse);
            } else {
                System.out.println("No found students for the selected course.");
            }
        } catch (InvalidIdException e) {
            LOGGER.info("Error getting course ID: " + e.getMessage());
        }
    }
}
