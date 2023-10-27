package ua.foxminded.javaspring.consoleMenu.service;

import ua.foxminded.javaspring.consoleMenu.model.Course;

public interface CourseService {
    boolean addCourse(Course course);

    boolean isValidCourseID(Course course);
}
