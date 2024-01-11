//package ua.foxminded.javaspring.consoleMenu.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import ua.foxminded.javaspring.consoleMenu.dao.StudentDAO;
//import ua.foxminded.javaspring.consoleMenu.model.Course;
//import ua.foxminded.javaspring.consoleMenu.model.Student;
//import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
//import ua.foxminded.javaspring.consoleMenu.service.impl.StudentServiceImpl;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class StudentServiceImplTest {
//
//    @Mock
//    private StudentDAO studentDAO;
//
//    @InjectMocks
//    private StudentServiceImpl studentService;
//
//    @BeforeEach
//    void init() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void saveStudent_shouldReturnTrue_whenIsAddedSuccessfully() {
//        when(studentDAO.addStudent(any(Student.class))).thenReturn(true);
//
//        assertThat(studentService.saveStudent(studentInitial())).isTrue();
//
//        verify(studentDAO).addStudent(any(Student.class));
//    }
//
//    @Test
//    void allCoursesOfStudent_shouldReturnListOfAllStudentCourses_whenIsCalled() {
//        List<StudentAtCourse> coursesOfStudent = new ArrayList<>();
//        Student student = studentInitial();
//        coursesOfStudent.add(new StudentAtCourse(student, new Course(1L)));
//        coursesOfStudent.add(new StudentAtCourse(student, new Course(2L)));
//        coursesOfStudent.add(new StudentAtCourse(student, new Course(3L)));
//
//        when(studentDAO.studentCourses(any(Student.class))).thenReturn(coursesOfStudent);
//
//        List<StudentAtCourse> result = studentService.allCoursesOfStudent(student);
//
//        int courseID = 1;
//
//        for (StudentAtCourse course : result) {
//            assertThat(course.getStudent()).usingRecursiveComparison().isSameAs(student);
//            assertThat(course.getCourse().getCourseID()).isEqualTo(courseID);
//            courseID++;
//        }
//
//        verify(studentDAO).studentCourses(any(Student.class));
//    }
//
//    @Test
//    void deleteStudent_shouldReturnTrue_whenDeletedSuccessfully() {
//        when(studentDAO.deleteStudent(any(Student.class))).thenReturn(true);
//
//        assertThat(studentService.deleteStudent(studentInitial())).isTrue();
//
//        verify(studentDAO).deleteStudent(any(Student.class));
//    }
//
//    @Test
//    void getStudentByID_shouldReturnStudentObjectInstance_whenIsFound() {
//        int studentID = 1;
//        Student student = studentInitial();
//
//        when(studentDAO.getStudentByID(any(Student.class))).thenReturn(Optional.of(student));
//
//        Student result = studentService.getStudentByID(new Student(studentID));
//
//        assertThat(student).usingRecursiveComparison().isEqualTo(result);
//
//        verify(studentDAO).getStudentByID(any(Student.class));
//    }
//
//    private Student studentInitial() {
//        return new Student(1L, "firstName", "lastName");
//    }
//
//}
