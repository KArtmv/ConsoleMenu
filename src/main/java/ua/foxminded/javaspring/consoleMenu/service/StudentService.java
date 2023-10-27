package ua.foxminded.javaspring.consoleMenu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

@Service
public interface StudentService {

    Student getStudentByID(Student student);

    boolean saveStudent(Student student);

    List<StudentAtCourse> allCoursesOfStudent(Student studentID);

    boolean deleteStudent(Student studentID);
}
