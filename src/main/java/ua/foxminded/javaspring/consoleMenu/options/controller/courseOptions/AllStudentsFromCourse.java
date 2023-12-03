package ua.foxminded.javaspring.consoleMenu.options.controller.courseOptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.console.input.ItemID;
import ua.foxminded.javaspring.consoleMenu.options.print.ItemPrint;
import ua.foxminded.javaspring.consoleMenu.service.CourseService;

import java.util.List;

public class AllStudentsFromCourse {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllStudentsFromCourse.class);

    private CourseService courseService;
    private ItemID<Course> inputID;
    private ItemPrint<Course> print;

    @Autowired
    public AllStudentsFromCourse(CourseService courseService, ItemID<Course> inputID, ItemPrint<Course> print) {
        this.courseService = courseService;
        this.inputID = inputID;
        this.print = print;
    }

    public void showAllStudentsFromCourse() {
        try {
            print.printAllAvailableItems();
            System.out.println("Choose and input the ID of the course from the list to view all students enrolled in this course.");
            List<StudentAtCourse> studentsFromCourse = courseService.allStudentsFromCourse(new Course(inputID.inputID()));

            if (!CollectionUtils.isEmpty(studentsFromCourse)) {
                viewAllStudents(studentsFromCourse);
            } else {
                System.out.println("No found students for the selected course.");
            }
        } catch (InvalidIdException e) {
            LOGGER.info("Error getting course ID: " + e.getMessage());
        }
    }

    private void viewAllStudents(List<StudentAtCourse> studentsFromCourse) {
        System.out.printf("At course: %s, study next students:\n", studentsFromCourse.get(0).getCourse().getCourseName());
        studentsFromCourse.forEach(student -> System.out.printf("%s %s\n", student.getStudent().getFirstName(), student.getStudent().getLastName()));
    }
}
