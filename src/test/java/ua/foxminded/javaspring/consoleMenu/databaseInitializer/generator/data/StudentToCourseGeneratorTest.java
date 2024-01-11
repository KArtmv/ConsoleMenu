package ua.foxminded.javaspring.consoleMenu.data.generator;

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
        expect.add(new StudentAtCourse(new Student(1L, "firstname1", "lastName1", 1L), new Course(1L)));
        expect.add(new StudentAtCourse(new Student(1L, "firstname1", "lastName1", 1L), new Course(2L)));
        expect.add(new StudentAtCourse(new Student(2L, "firstname2", "lastName2", 2L), new Course(1L)));
        expect.add(new StudentAtCourse(new Student(3L, "firstname3", "lastName3", 3L), new Course(1L)));
        expect.add(new StudentAtCourse(new Student(3L, "firstname3", "lastName3", 3L), new Course(2L)));
        expect.add(new StudentAtCourse(new Student(3L, "firstname3", "lastName3", 3L), new Course(3L)));

        when(dataConduct.getCourses()).thenReturn(courses);
        when(dataConduct.getStudents()).thenReturn(students);
        when(amountLimit.getMaxCountCoursesOfStudent()).thenReturn(maxCountCoursesOfStudent);
        when(random.getInt(maxCountCoursesOfStudent)).thenReturn(2).thenReturn(1).thenReturn(3);
        when(random.getLong(courses.size())).thenReturn(1L, 2L).thenReturn(1L).thenReturn(1L, 2L, 3L);

        List<StudentAtCourse> result = studentToCourseGenerator.generate();
        
        assertAll(
        		() -> assertThat(result).hasSize(6),
        		() -> assertThat(result).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expect)
        		);

        verify(dataConduct).getCourses();
        verify(dataConduct).getStudents();
        verify(amountLimit).getMaxCountCoursesOfStudent();
        verify(random, times(3)).getInt(courses.size());
        verify(random, times(6)).getLong(maxCountCoursesOfStudent);
    }
}
