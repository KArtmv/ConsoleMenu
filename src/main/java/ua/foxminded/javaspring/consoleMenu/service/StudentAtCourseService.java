package ua.foxminded.javaspring.consoleMenu.service;

import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

import java.util.List;

public interface StudentAtCourseService {
    List<StudentAtCourse> allStudentsFromCourse(Course course);

    boolean addStudentToCourse(StudentAtCourse studentAtCourse);

    boolean removeStudentFromCourse(StudentAtCourse studentAtCourse);

    boolean removeStudentFromAllTheirCourses(Student student);
}
