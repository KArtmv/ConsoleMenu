package ua.foxminded.javaspring.consoleMenu.options.console.output;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.dao.CourseDAO;
import ua.foxminded.javaspring.consoleMenu.model.Course;

import java.util.List;

public class OutputListOfCourses {

    private static final Logger LOGGER = LoggerFactory.getLogger(OutputListOfCourses.class);

    private CourseDAO courseDAO;

    @Autowired
    public OutputListOfCourses(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void viewAllCourses() {
        List<Course> courses = courseDAO.listOfItems();
        if (!CollectionUtils.isEmpty(courses)) {
            output(courses);
        } else {
            LOGGER.info("Failed to get list of courses.");
        }
    }

    private void output(List<Course> courses) {
        courses.forEach(course -> System.out.printf("ID: %d, Course name: %s,\n   Course description: %s.\n",
                course.getCourseID(), course.getCourseName(), course.getCourseDescription()));
    }
}
