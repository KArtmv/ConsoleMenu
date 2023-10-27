package ua.foxminded.javaspring.consoleMenu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.foxminded.javaspring.consoleMenu.dao.StudentDAO;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public boolean saveStudent(Student student) {
        return studentDAO.addStudent(student);
    }

    @Override
    public List<StudentAtCourse> allCoursesOfStudent(Student studentID) {
        return studentDAO.studentCourses(studentID);
    }

    @Override
    public boolean deleteStudent(Student studentID) {
        return studentDAO.deleteStudent(studentID);
    }

    @Override
    public Student getStudentByID(Student student) {
        Optional<Student> result = studentDAO.getStudentByID(student);

        Student resultStudent = null;
        if (result.isPresent()) {
            resultStudent = result.get();
        }
        return resultStudent;
    }
}
