package ua.foxminded.javaspring.consoleMenu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.consoleMenu.dao.CourseDAO;
import ua.foxminded.javaspring.consoleMenu.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.consoleMenu.exception.InvalidIdException;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.service.CourseService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseServiceImpl implements CourseService {

    private StudentAtCourseDAO studentAtCourseDAO;
    private CourseDAO courseDAO;

    @Autowired
    public CourseServiceImpl(StudentAtCourseDAO studentAtCourseDAO, CourseDAO courseDAO) {
        this.studentAtCourseDAO = studentAtCourseDAO;
        this.courseDAO = courseDAO;
    }

    @Override
    public List<StudentAtCourse> allStudentsFromCourse(Course course) {
       List<StudentAtCourse> result;
        try {
            result = studentAtCourseDAO.allStudentsFromCourse(courseDAO.getItemByID(course)
                    .orElseThrow(NoSuchElementException::new));
        } catch (NoSuchElementException e) {
            throw new InvalidIdException("No founded course by given ID: " + course.getCourseID());
        }
        return result;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDAO.getAll();
    }
}
