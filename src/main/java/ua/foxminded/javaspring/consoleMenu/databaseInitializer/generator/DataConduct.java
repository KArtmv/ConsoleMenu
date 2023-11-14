package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data.CourseGenerator;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data.GroupGenerator;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data.StudentGenerator;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data.StudentToCourseGenerator;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

import java.util.Collections;
import java.util.List;

public class DataConduct {

    private List<Student> students;
    private List<Course> courses;
    private List<Group> groups;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
