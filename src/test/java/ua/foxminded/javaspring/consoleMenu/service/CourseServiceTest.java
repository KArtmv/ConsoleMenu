package ua.foxminded.javaspring.consoleMenu.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.consoleMenu.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.service.impl.StudentAtCourseServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentAtCourseServiceImplTest {

    @Mock
    private StudentAtCourseDAO studentAtCourseDAO;

    @InjectMocks
    private StudentAtCourseServiceImpl courseService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void allStudentsFromCourse_shouldReturnListOfStudentAtCourse_whenIsCalled() {
        int courseID = 3;

        List<StudentAtCourse> studentsAtCourse = new ArrayList<>();
        for (int ID = 1; ID <= 3; ID++) {
            studentsAtCourse.add(new StudentAtCourse((long) ID, studentInitial(), courseInitial()));
        }

        when(studentAtCourseDAO.allStudentsFromCourse(any(Course.class))).thenReturn(studentsAtCourse);

        List<StudentAtCourse> result = courseService.allStudentsFromCourse(new Course((long) courseID));

        for (StudentAtCourse student : result) {
            assertThat((student.getEnrollmentID() > 0)).isTrue();
            assertThat(student.getStudent()).isNotNull();
            assertThat(student.getCourse()).isNotNull();
        }

        verify(studentAtCourseDAO).allStudentsFromCourse(any(Course.class));
    }

    @Test
    void addStudentToCourse_shouldReturnTrue_whenStudentIsAddedSuccessfully() {
        StudentAtCourse studentAtCourse = new StudentAtCourse(studentInitial(),
                courseInitial());
        when(studentAtCourseDAO.addItem(any(StudentAtCourse.class))).thenReturn(true);

        assertThat(courseService.addStudentToCourse(studentAtCourse)).isTrue();

        verify(studentAtCourseDAO).addItem(any(StudentAtCourse.class));
    }

    @Test
    void removeStudentFromCourse_shouldReturnTrue_whenStudentRemovedSuccessfully() {
        when(studentAtCourseDAO.removeStudentFromCourse(any(StudentAtCourse.class))).thenReturn(true);

        assertThat(courseService.removeStudentFromCourse(new StudentAtCourse(1L, studentInitial(),
                courseInitial()))).isTrue();

        verify(studentAtCourseDAO).removeStudentFromCourse(any(StudentAtCourse.class));
    }

    @Test
    void removeStudentFromAllTheirCourses_shouldReturnTrue_whenRemovedSuccessfully() {
        when(studentAtCourseDAO.removeStudentFromAllTheirCourses(any(Student.class))).thenReturn(true);

        assertThat(courseService.removeStudentFromAllTheirCourses(studentInitial())).isTrue();

        verify(studentAtCourseDAO).removeStudentFromAllTheirCourses(any(Student.class));
    }

    private Student studentInitial() {
        return new Student("firstName", "lastName");
    }

    private Course courseInitial() {
        return new Course("courseName", "courseDescription");
    }
}
