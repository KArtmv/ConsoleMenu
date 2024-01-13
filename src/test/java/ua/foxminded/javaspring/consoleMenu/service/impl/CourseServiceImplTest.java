package ua.foxminded.javaspring.consoleMenu.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.consoleMenu.dao.CourseDAO;
import ua.foxminded.javaspring.consoleMenu.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.service.impl.CourseServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseServiceImplTest {

    @Mock
    private StudentAtCourseDAO studentAtCourseDAO;
    @Mock
    private CourseDAO courseDAO;

    public static final  Course course = new Course(1L);

    @InjectMocks
    private CourseServiceImpl courseService;

    public static Stream<Arguments> optionalGenerate() {
        return Stream.of(
                Arguments.of(Optional.of(course)),
                Arguments.of(Optional.empty())
        );
    }

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
    @ParameterizedTest
    @MethodSource("optionalGenerate")
    void allStudentsFromCourse_shouldReturnListOfStudentsToEnrollmentWithCourse_whenCourseIsExist(Optional<Course> course) {
        Course course2 = new Course(1L);
//        Optional<Course> optional = Optional.of(course2);

        List<StudentAtCourse> studentsAtCourse = new ArrayList<>();
        studentsAtCourse.add(new StudentAtCourse(new Student("firstName1", "lastName1"), course2));
        studentsAtCourse.add(new StudentAtCourse(new Student("firstName2", "lastName2"), course2));
        studentsAtCourse.add(new StudentAtCourse(new Student("firstName3", "lastName3"), course2));

//        doReturn(course).when(courseDAO).getItemByID(any(Course.class))/*.orElseThrow(NoSuchElementException::new)*/;
//        doReturn(studentsAtCourse).when(studentAtCourseDAO).allStudentsFromCourse(course2);
//
        assertThat(courseService.allStudentsFromCourse(course2)).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(studentsAtCourse).;
//
////        assertAll(
//                assertThat(Optional.ofNullable(courseService.allStudentsFromCourse(course2)).orElseThrow(NoSuchElementException::new));
////        );


        when(courseDAO.getItemByID(course2)).thenReturn(course).thenThrow();
        when(studentAtCourseDAO.allStudentsFromCourse(course2).orElseThrow(studentsAtCourse);
//
//        assertAll(
//
//        () ->  assertThat(course).isPresent(),
//        () ->  course.ifPresent(course1 -> assertThat(courseService.allStudentsFromCourse(course.get()))
//                .usingRecursiveComparison()
//                .ignoringCollectionOrder()
//                .isEqualTo(studentsAtCourse)),
//
//        () -> assertThrows(NoSuchElementException.class, () -> courseService.allStudentsFromCourse(course.get())));






    }

//    @Test
//    void isValidCourseID_shouldReturnTrue_whenCourseIDIsValid() {
//        when(courseDAO.isValidCourseID(any(Course.class))).thenReturn(true);
//
//        assertThat(courseService.isValidCourseID(new Course(1L))).isTrue();
//
//        verify(courseDAO).isValidCourseID(any(Course.class));
//    }
}
