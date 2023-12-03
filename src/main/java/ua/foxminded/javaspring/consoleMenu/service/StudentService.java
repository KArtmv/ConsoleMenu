package ua.foxminded.javaspring.consoleMenu.service;

import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

@Service
public interface StudentService {
    void addNewStudent(Student student);

    void deleteStudent(Student studentID);

    void addStudentToCourse(StudentAtCourse studentAtCourse);

    void removeStudentFromCourse(StudentAtCourse studentAtCourse);
}
