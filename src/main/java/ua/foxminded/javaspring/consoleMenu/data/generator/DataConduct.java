package ua.foxminded.javaspring.consoleMenu.data.generator;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

import java.util.List;

public class DataConduct {

    private StudentGenerator studentGenerator;
    private CourseGenerator courseGenerator;
    private GroupGenerator groupGenerator;
    private StudentToCourseGenerator studentToCourse;

    private List<Student> students;
    private List<Course> courses;
    private List<Group> groups;

    @Autowired
    public DataConduct(StudentGenerator studentGenerator, CourseGenerator courseGenerator,
                       GroupGenerator groupGenerator, StudentToCourseGenerator studentToCourse) {
        this.studentGenerator = studentGenerator;
        this.courseGenerator = courseGenerator;
        this.groupGenerator = groupGenerator;
        this.studentToCourse = studentToCourse;
    }

    public List<Student> createStudents() {

        if (students == null) {
            createGroups();
            students = studentGenerator.generate(groups);
        }
        return students;
    }

    public List<Group> createGroups() {
        if (groups == null) {
            groups = groupGenerator.generate();
        }
        return groups;
    }

    public List<Course> createCourses() {
        if (courses == null) {
            courses = courseGenerator.generate();
        }
        return courses;
    }

    public List<StudentAtCourse> createRelationStudentCourse() {
        createCourses();
        createStudents();
        return studentToCourse.addStudentToCourse(students, courses.size());
    }
}
