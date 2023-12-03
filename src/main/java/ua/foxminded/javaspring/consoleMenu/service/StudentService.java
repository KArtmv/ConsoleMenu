package ua.foxminded.javaspring.consoleMenu.service;

import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

import java.util.List;

@Service
public interface StudentService {
    void saveStudent(Student student);

    List<StudentAtCourse> allCoursesOfStudent(Student studentID);

    void deleteStudent(Student studentID);

    Student getStudentByID(Student student);

    boolean addStudentToCourse(StudentAtCourse studentAtCourse);

    void removeStudentFromCourse(StudentAtCourse studentAtCourse);

    void removeStudentFromAllTheirCourses(Student student);
}
