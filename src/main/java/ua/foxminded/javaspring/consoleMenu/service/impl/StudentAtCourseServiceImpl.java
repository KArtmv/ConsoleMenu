package ua.foxminded.javaspring.consoleMenu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.consoleMenu.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.service.StudentAtCourseService;

import java.util.List;

@Service
public class StudentAtCourseServiceImpl implements StudentAtCourseService {

    private StudentAtCourseDAO studentAtCourseDAO;

    @Autowired
    public StudentAtCourseServiceImpl(StudentAtCourseDAO studentAtCourseDAO) {
        this.studentAtCourseDAO = studentAtCourseDAO;
    }

    @Override
    public List<StudentAtCourse> allStudentsFromCourse(Course course) {
        return studentAtCourseDAO.allStudentsFromCourse(course);
    }

    @Override
    public boolean addStudentToCourse(StudentAtCourse studentAtCourse) {
        return studentAtCourseDAO.addItem(studentAtCourse);
    }

    @Override
    public boolean removeStudentFromCourse(StudentAtCourse studentAtCourse) {
        return studentAtCourseDAO.removeStudentFromCourse(studentAtCourse);
    }

    @Override
    public boolean removeStudentFromAllTheirCourses(Student student) {
        return studentAtCourseDAO.removeStudentFromAllTheirCourses(student);
    }
}
