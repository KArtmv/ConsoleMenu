package ua.foxminded.javaspring.consoleMenu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.consoleMenu.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.consoleMenu.dao.StudentDAO;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDAO studentDAO;
    private StudentAtCourseDAO studentAtCourseDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO, StudentAtCourseDAO studentAtCourseDAO) {
        this.studentDAO = studentDAO;
        this.studentAtCourseDAO = studentAtCourseDAO;
    }

    @Override
    public void saveStudent(Student student) {
        studentDAO.addItem(student);
    }

    @Override
    public List<StudentAtCourse> allCoursesOfStudent(Student studentID) {
        return studentDAO.studentCourses(studentID);
    }

    @Override
    public void deleteStudent(Student studentID) {
        studentDAO.removeStudent(studentID);
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

    @Override
    public boolean addStudentToCourse(StudentAtCourse studentAtCourse) {
        return studentAtCourseDAO.addItem(studentAtCourse);
    }

    @Override
    public void removeStudentFromCourse(StudentAtCourse studentAtCourse) {
        studentAtCourseDAO.removeStudentFromCourse(studentAtCourse);
    }

    @Override
    public void removeStudentFromAllTheirCourses(Student student) {
        studentAtCourseDAO.removeStudentFromAllTheirCourses(student);
    }
}
