package ua.foxminded.javaspring.consoleMenu.data.generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.consoleMenu.data.RandomNumber;
import ua.foxminded.javaspring.consoleMenu.data.generator.sourceData.CountConfig;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.pattern.InitializeObject;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StudentToCourseGeneratorTest {
    @Mock
    private RandomNumber randomNumber;

    @Mock
    private CountConfig countConfig;

    @InjectMocks
    private StudentToCourseGenerator studentToCourseGenerator;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generate_shouldReturnListOfStudentAtCourse_whenIsCorrect() {
        InitializeObject initializeObject = new InitializeObject();
        List<Student> students = initializeObject.studentsListInit();

        int countCourses = 10;
        int maxCountCoursesOfStudent = 3;

        when(countConfig.getMaxCountCoursesOfStudent()).thenReturn(maxCountCoursesOfStudent);
        when(randomNumber.generateBetweenOneAndInputNumber(maxCountCoursesOfStudent)).thenReturn(1, 2, 3);
        when(randomNumber.generateBetweenOneAndInputNumber(countCourses)).thenReturn(4, 5, 6, 7, 8, 9);

        List<StudentAtCourse> studentAtCourses = studentToCourseGenerator.addStudentToCourse(students, countCourses);

        for (StudentAtCourse studentAtCourse : studentAtCourses) {
            Student student = studentAtCourse.getStudent();
            Course course = studentAtCourse.getCourse();

            assertThat(student.getStudentID() > 0).isTrue();
            assertThat((course.getCourseID() > 0) && (course.getCourseID() <= countCourses)).isTrue();
        }

        verify(countConfig).getMaxCountCoursesOfStudent();
        verify(randomNumber, times(3)).generateBetweenOneAndInputNumber(maxCountCoursesOfStudent);
        verify(randomNumber, times(6)).generateBetweenOneAndInputNumber(countCourses);
    }
}
