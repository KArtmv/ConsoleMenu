package ua.foxminded.javaspring.consoleMenu.options.output;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.dao.CourseDAO;
import ua.foxminded.javaspring.consoleMenu.model.Course;

import java.util.List;

public class OutputListOfCourses {

    private CourseDAO courseDAO;

    @Autowired
    public OutputListOfCourses(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void viewAllCourses(){
        List<Course> courses = courseDAO.listOfItems();
        if (!CollectionUtils.isEmpty(courses)){
            output(courses);
        }
    }

    private void output(List<Course> courses){
        for (Course course: courses) {
            System.out.printf("ID: %d, Course name: %s,\n   Course description: %s.\n",
                    course.getCourseID(), course.getCourseName(), course.getCourseDescription());
        }
    }
}
