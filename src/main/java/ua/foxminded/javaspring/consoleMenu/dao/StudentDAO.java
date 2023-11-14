package ua.foxminded.javaspring.consoleMenu.dao;

import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

import java.util.List;
import java.util.Optional;

public interface StudentDAO  extends DAO<Student> {
    List<StudentAtCourse> studentCourses(Student student);

    boolean removeStudent(Student student);

    Optional<Student> getByItemID(Student student);
}
