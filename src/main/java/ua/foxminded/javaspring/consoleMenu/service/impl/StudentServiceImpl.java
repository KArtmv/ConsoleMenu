package ua.foxminded.javaspring.consoleMenu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.consoleMenu.dao.TablesDAO;
import ua.foxminded.javaspring.consoleMenu.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.consoleMenu.dao.StudentDAO;
import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.service.StudentService;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDAO studentDAO;
    private StudentAtCourseDAO studentAtCourseDAO;
    private TablesDAO<Group> groupDAO;
    private TablesDAO<Course> courseDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO, StudentAtCourseDAO studentAtCourseDAO, TablesDAO<Group> groupDAO, TablesDAO<Course> courseDAO) {
        this.studentDAO = studentDAO;
        this.studentAtCourseDAO = studentAtCourseDAO;
        this.groupDAO = groupDAO;
        this.courseDAO = courseDAO;
    }

    @Override
    public boolean addNewStudent(Student student) {
        try {
            if (groupDAO.getItemByID(new Group(student.getGroupID())).isPresent()) {
                return studentDAO.addItem(student);
            }
        } catch (EmptyResultDataAccessException e) {
            throw new InvalidIdException("Not found group with received ID: " + student.getGroupID());
        }
        return false;
    }

    @Override
    public boolean deleteStudent(Student student) {
        studentAtCourseDAO.removeStudentFromAllTheirCourses(student);
        return studentDAO.removeStudent(student);
    }

    @Override
    public boolean addStudentToCourse(StudentAtCourse studentAtCourse) {
        try {
            if (courseDAO.getItemByID(studentAtCourse.getCourse()).isPresent()) {
                return studentAtCourseDAO.addItem(studentAtCourse);
            }
        } catch (EmptyResultDataAccessException e) {
            throw new InvalidIdException("Not found course with received ID: " + studentAtCourse.getCourse().getCourseID());
        }
        return false;
    }

    @Override
    public boolean removeStudentFromCourse(StudentAtCourse studentAtCourse) {
        if (studentDAO.studentCourses(studentAtCourse.getStudent()).stream()
                .anyMatch(id -> Objects.equals(id.getEnrollmentID(), studentAtCourse.getEnrollmentID()))) {
            return studentAtCourseDAO.removeStudentFromCourse(studentAtCourse);
        } else {
            throw new InvalidIdException(String.format(
                    "Received enrollment ID: %s, is not found or not relate to given student: ID: %s ",
                    studentAtCourse.getEnrollmentID(), studentAtCourse.getStudent().getStudentID()));
        }
    }

    @Override
    public Student getStudent(Student student) {
        Student result = null;
        try {
            if (studentDAO.getItemByID(student).isPresent()) {
                result = studentDAO.getItemByID(student).get();
            }
        } catch (EmptyResultDataAccessException e) {
            throw new InvalidIdException("Not found student with given ID: " + student.getStudentID());
        }
        return result;
    }

    @Override
    public List<StudentAtCourse> getAllCoursesOfStudent(Student student) {
        return studentDAO.studentCourses(student);
    }
}
