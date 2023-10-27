package ua.foxminded.javaspring.consoleMenu.service;

import java.util.List;

import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

public interface StudentAtCourseService {
    List<StudentAtCourse> allStudentsFromCourse(Course course);

    boolean addStudentToCourse(Student student, Course course);

    boolean removeStudentFromCourse(StudentAtCourse studentAtCourse);

    boolean removeStudentFromAllTheirCourses(Student student);
}
