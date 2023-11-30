package ua.foxminded.javaspring.consoleMenu.options.print;

import ua.foxminded.javaspring.consoleMenu.model.Course;

import java.util.List;

public class CoursePrinter implements Print<Course> {

    @Override
    public void printItems(List<Course> courses) {
        courses.forEach(course -> System.out.printf("ID: %d, Course name: %s,\n   Course description: %s.\n",
                course.getCourseID(), course.getCourseName(), course.getCourseDescription()));
    }
}
