package ua.foxminded.javaspring.consoleMenu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.consoleMenu.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.service.CourseService;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private StudentAtCourseDAO studentAtCourseDAO;

    @Autowired
    public CourseServiceImpl(StudentAtCourseDAO studentAtCourseDAO) {
        this.studentAtCourseDAO = studentAtCourseDAO;
    }

    @Override
    public List<StudentAtCourse> allStudentsFromCourse(Course course) {
        return studentAtCourseDAO.allStudentsFromCourse(course);
    }
}
