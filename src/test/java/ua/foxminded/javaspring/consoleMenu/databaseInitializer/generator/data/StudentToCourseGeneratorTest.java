package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data.StudentToCourseGenerator;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.pattern.InitializeObject;
import ua.foxminded.javaspring.consoleMenu.util.AmountLimit;
import ua.foxminded.javaspring.consoleMenu.util.MyRandom;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

class StudentToCourseGeneratorTest {
    @Mock
    DataConduct dataConduct;
    @Mock
    AmountLimit amountLimit;
    @Mock
    private MyRandom random;
    @InjectMocks
    private StudentToCourseGenerator studentToCourseGenerator;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generate_shouldReturnListOfStudentAtCourse_whenIsCorrect() {
        InitializeObject initializeObject = new InitializeObject();
        List<Student> students =  initializeObject.studentsListInit();
        List<Course> courses = initializeObject.coursesListInit();
        int maxCountCoursesOfStudent = 3;

        List<StudentAtCourse> expect = new ArrayList<>();
        expect.add(new StudentAtCourse(students.get(0), courses.get(0)));
        expect.add(new StudentAtCourse(students.get(0), courses.get(1)));
        expect.add(new StudentAtCourse(students.get(1), courses.get(0)));
        expect.add(new StudentAtCourse(students.get(2), courses.get(0)));
        expect.add(new StudentAtCourse(students.get(2), courses.get(1)));
        expect.add(new StudentAtCourse(students.get(2), courses.get(2)));

        when(dataConduct.getCourses()).thenReturn(courses);
        when(dataConduct.getStudents()).thenReturn(students);
        when(amountLimit.getMaxCountCoursesOfStudent()).thenReturn(maxCountCoursesOfStudent);
        when(random.getInt(maxCountCoursesOfStudent)).thenReturn(2).thenReturn(1).thenReturn(3);
        when(random.getInt(courses.size())).thenReturn(0, 1).thenReturn(0).thenReturn(0, 1, 2);

        List<StudentAtCourse> result = studentToCourseGenerator.generate();
        
        assertAll(
        		() -> assertThat(result).hasSize(6),
        		() -> assertThat(result).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expect)
        		);

        verify(dataConduct).getCourses();
        verify(dataConduct).getStudents();
        verify(amountLimit).getMaxCountCoursesOfStudent();
        verify(random, times(3)).getInt(maxCountCoursesOfStudent);
        verify(random, times(6)).getInt(courses.size());
    }
}
