package ua.foxminded.javaspring.consoleMenu.options.console.output;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.menu.Menu;
import ua.foxminded.javaspring.consoleMenu.service.CourseService;
import ua.foxminded.javaspring.consoleMenu.service.GroupService;

import java.util.List;

public class ConsolePrinter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsolePrinter.class);
    private GroupService groupService;
    private CourseService courseService;
    private Menu menu;

    @Autowired
    public ConsolePrinter(GroupService groupService, CourseService courseService, Menu menu) {
        this.groupService = groupService;
        this.courseService = courseService;
        this.menu = menu;
    }

    public void viewAllCoursesOfStudent(List<StudentAtCourse> allStudentCourses) {
        print(String.format("Student: %s %s, studies at next courses:",
                allStudentCourses.get(0).getStudent().getFirstName(), allStudentCourses.get(0).getStudent().getLastName()));

        allStudentCourses.forEach(studentAtCourse -> print(String.format(
                "ID: %d, course: %s,\n   Description of course: %s.",
                studentAtCourse.getEnrollmentID(),
                studentAtCourse.getCourse().getCourseName(),
                studentAtCourse.getCourse().getCourseDescription())));
    }

    public void viewAllStudentsFromCourse(List<StudentAtCourse> studentsFromCourse) {
        print(String.format("At course: %s, study next students:", studentsFromCourse.get(0).getCourse().getCourseName()));
        studentsFromCourse.forEach(student ->
                print(String.format("%s %s\n", student.getStudent().getFirstName(), student.getStudent().getLastName())));
    }

    public void viewAmountStudentAtGroup(List<CounterStudentsAtGroup> studentsAtGroups) {
        studentsAtGroups.forEach(studentsAtGroup ->
                print(String.format("%d of students at group: %s.",
                        studentsAtGroup.getStudentsCount(), studentsAtGroup.getGroupName())));
    }

    public void printAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (!CollectionUtils.isEmpty(courses)) {
            courses.forEach(course -> print(String.format("ID: %d, Course name: %s,\n   Course description: %s.",
                    course.getCourseID(), course.getCourseName(), course.getCourseDescription())));
        } else {
            LOGGER.info("Failed to get list of courses.");
        }
    }

    public void printAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        if (!CollectionUtils.isEmpty(groups)) {
            groups.forEach(group -> print(String.format("ID: %d,  group name: %s.", group.getGroupID(), group.getGroupName())));
        } else {
            LOGGER.info("Failed to get list of courses.");
        }
    }

    public void printMenu() {
        print(menu.getOptions());
    }

    public void print(String string){
        System.out.println(string);
    }
}
