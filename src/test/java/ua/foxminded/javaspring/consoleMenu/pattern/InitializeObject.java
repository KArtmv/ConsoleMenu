package ua.foxminded.javaspring.consoleMenu.pattern;

import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

import java.util.ArrayList;
import java.util.List;

public class InitializeObject {

    public List<Student> studentsListInit() {
        List<Student> students = new ArrayList<>();
        for (int id = 1; id <= 3; id++) {
            students.add(new Student((long) id, "firstName", "lastName", (long) id));
        }
        return students;
    }

    public List<Course> coursesListInit() {
        List<Course> courses = new ArrayList<>();
        for (int id = 1; id <= 3; id++) {
            courses.add(new Course((long) id, "courseName", "courseDescription"));
        }
        return courses;
    }

    public List<Group> groupsListInit() {
        List<Group> groups = new ArrayList<>();
        for (int id = 1; id <= 3; id++) {
            groups.add(new Group((long) id, "groupName"));
        }
        return groups;
    }

    public List<StudentAtCourse> studentAtCourseListInit(){
        List<StudentAtCourse> studentAtCourses = new ArrayList<>();
        for (int id = 1; id <= 3; id++) {
            studentAtCourses.add(new StudentAtCourse((long) id, new Student((long) id, "firstname", "lastName"),
                    new Course((long) id, "courseName", "courseDescription")));
        }
        return studentAtCourses;
    }
}
