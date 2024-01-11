package ua.foxminded.javaspring.consoleMenu.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.consoleMenu.dao.CourseDAO;
import ua.foxminded.javaspring.consoleMenu.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.service.impl.CourseServiceImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CourseServiceImplTest {

    @Mock
    private StudentAtCourseDAO studentAtCourseDAO;
    @Mock
    private CourseDAO courseDAO;

    @InjectMocks
    private CourseServiceImpl courseService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCourse_shouldReturnTrue_whenCourseIsAdded() {
        when(courseDAO.addCourse(any(Course.class))).thenReturn(true);

        assertThat(courseService.addCourse(new Course(1L, "courseName", "courseDescription"))).isTrue();

        verify(courseDAO).addCourse(any(Course.class));
    }

    @Test
    void isValidCourseID_shouldReturnTrue_whenCourseIDIsValid() {
        when(courseDAO.isValidCourseID(any(Course.class))).thenReturn(true);

        assertThat(courseService.isValidCourseID(new Course(1L))).isTrue();

        verify(courseDAO).isValidCourseID(any(Course.class));
    }
}
