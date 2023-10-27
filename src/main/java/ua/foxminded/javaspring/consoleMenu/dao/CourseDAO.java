package ua.foxminded.javaspring.consoleMenu.dao;

import ua.foxminded.javaspring.consoleMenu.model.Course;

public interface CourseDAO {
    boolean addCourse(Course course);

    boolean isValidCourseID(Course course);

    boolean isCourseTableExist(String sqlQuery);

    void createCourseTable(String sqlQuery);

    boolean isCourseTableEmpty();
}
