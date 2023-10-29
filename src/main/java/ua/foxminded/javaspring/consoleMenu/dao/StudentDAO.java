package ua.foxminded.javaspring.consoleMenu.dao;

import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

import java.util.List;
import java.util.Optional;

public interface StudentDAO {

    Optional<Student> getStudentByID(Student student);

    boolean deleteStudent(Student student);

    List<StudentAtCourse> studentCourses(Student student);

    boolean addStudent(Student student);

    boolean isValidStudentID(Student student);

    boolean isTableExist(String sqlQuery);

    void createStudentTable(String sqlQuery);

    boolean isTableEmpty();
}
