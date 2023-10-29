package ua.foxminded.javaspring.consoleMenu.data.generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.pattern.InitializeObject;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataConductTest {

    @Mock
    private StudentGenerator studentGenerator;

    @Mock
    private CourseGenerator courseGenerator;

    @Mock
    private GroupGenerator groupGenerator;

    @Mock
    private StudentToCourseGenerator studentToCourse;

    @InjectMocks
    private DataConduct dataConduct;

    private InitializeObject initializeObject = new InitializeObject();

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createStudents_shouldReturnListOfStudent_whenIsRun() {
        List<Student> students = initializeObject.studentsListInit();
        List<Group> groups = initializeObject.groupsListInit();

        when(studentGenerator.generate(groups)).thenReturn(students);
        when(groupGenerator.generate()).thenReturn(groups);

        List<Student> result = dataConduct.createStudents();

        for (Student student : result) {
            assertThat((student.getStudentID() > 0) && (student.getStudentID() <= 3)).isTrue();
            assertThat(student.getFirstName()).isNotEmpty();
            assertThat(student.getLastName()).isNotEmpty();
            assertThat((student.getGroupID() > 0) && (student.getGroupID() <= 3)).isTrue();
        }
        verify(studentGenerator).generate(groups);
        verify(groupGenerator).generate();

    }

    @Test
    void createGroups_shouldReturnListOfGroup_whenIsRun() {
        List<Group> groups = initializeObject.groupsListInit();

        when(groupGenerator.generate()).thenReturn(groups);

        List<Group> result = dataConduct.createGroups();

        for (Group group : result) {
            assertThat((group.getGroupID() > 0) && (group.getGroupID() <= 3)).isTrue();
            assertThat(group.getGroupName()).isNotEmpty();
        }

        verify(groupGenerator).generate();
    }

    @Test
    void createCourses_shouldReturnListOfCourse_whenIsRun() {
        List<Course> courses = initializeObject.coursesListInit();

        when(courseGenerator.generate()).thenReturn(courses);

        List<Course> result = dataConduct.createCourses();

        for (Course course : result) {
            assertThat((course.getCourseID() > 0) && (course.getCourseID() <= 3)).isTrue();
            assertThat(course.getCourseName()).isNotEmpty();
            assertThat(course.getCourseDescription()).isNotEmpty();
        }

        verify(courseGenerator).generate();
    }

    @Test
    void createRelationStudentCourse_shouldReturnListOfStudentAtCourse_whenIsRan() {
        List<StudentAtCourse> studentAtCourses = initializeObject.studentAtCourseListInit();
        List<Student> students = initializeObject.studentsListInit();
        List<Course> courses = initializeObject.coursesListInit();
        List<Group> groups = initializeObject.groupsListInit();

        when(studentToCourse.addStudentToCourse(students, courses.size())).thenReturn(studentAtCourses);
        when(studentGenerator.generate(groups)).thenReturn(students);
        when(groupGenerator.generate()).thenReturn(groups);

        List<StudentAtCourse> result = dataConduct.createRelationStudentCourse();

        for (StudentAtCourse studentAtCourse : result) {
            Student student = studentAtCourse.getStudent();
            Course course = studentAtCourse.getCourse();

            assertThat((studentAtCourse.getEnrollmentID() > 0) && (studentAtCourse.getEnrollmentID() <= 3)).isTrue();

            assertThat((student.getStudentID() > 0) && (student.getStudentID() <= 3)).isTrue();
            assertThat(student.getFirstName()).isNotEmpty();
            assertThat(student.getLastName()).isNotEmpty();
            assertThat((student.getGroupID() > 0) && (student.getGroupID() <= 3)).isTrue();

            assertThat((course.getCourseID() > 0) && (course.getCourseID() <= 3)).isTrue();
            assertThat(course.getCourseName()).isNotEmpty();
            assertThat(course.getCourseDescription()).isNotEmpty();
        }
        verify(courseGenerator).generate();
        verify(groupGenerator).generate();
        verify(studentGenerator).generate(groups);
    }
}
