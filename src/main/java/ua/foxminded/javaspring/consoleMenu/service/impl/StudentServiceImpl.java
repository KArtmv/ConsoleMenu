package ua.foxminded.javaspring.consoleMenu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.consoleMenu.dao.StudentDAO;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public boolean saveStudent(Student student) {
        return studentDAO.addItem(student);
    }

    @Override
    public List<StudentAtCourse> allCoursesOfStudent(Student studentID) {
        return studentDAO.studentCourses(studentID);
    }

    @Override
    public boolean deleteStudent(Student studentID) {
        return studentDAO.removeStudent(studentID);
    }

    @Override
    public Student getStudentByID(Student student) {
        Optional<Student> result = studentDAO.getByItemID(student);

        Student resultStudent = null;
        if (result.isPresent()) {
            resultStudent = result.get();
        }
        return resultStudent;
    }
}
