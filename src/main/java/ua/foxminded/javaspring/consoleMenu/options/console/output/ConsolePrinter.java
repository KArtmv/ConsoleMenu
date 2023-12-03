package ua.foxminded.javaspring.consoleMenu.options.console.output;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import ua.foxminded.javaspring.consoleMenu.dao.DAO;
import ua.foxminded.javaspring.consoleMenu.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

import java.util.List;

public class ConsolePrinter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsolePrinter.class);
    private DAO<Group> groupDAO;
    private DAO<Course> courseDAO;

    @Autowired
    public ConsolePrinter(DAO<Group> groupDAO, DAO<Course> courseDAO) {
        this.groupDAO = groupDAO;
        this.courseDAO = courseDAO;
    }

    public void viewAllCoursesOfStudent(List<StudentAtCourse> allStudentCourses) {
        System.out.printf("Student: %s %s, studies at next courses:\n",
                allStudentCourses.get(0).getStudent().getFirstName(), allStudentCourses.get(0).getStudent().getLastName());

        allStudentCourses.forEach(studentAtCourse -> System.out.printf(
                "ID: %d, course: %s,\n   Description of course: %s.\n",
                studentAtCourse.getEnrollmentID(),
                studentAtCourse.getCourse().getCourseName(),
                studentAtCourse.getCourse().getCourseDescription()));
    }

    public void viewAllStudentsFromCourse(List<StudentAtCourse> studentsFromCourse) {
        System.out.printf("At course: %s, study next students:\n", studentsFromCourse.get(0).getCourse().getCourseName());
        studentsFromCourse.forEach(student -> System.out.printf("%s %s\n", student.getStudent().getFirstName(), student.getStudent().getLastName()));
    }

    public void viewAmountStudentAtGroup(List<CounterStudentsAtGroup> studentsAtGroups) {
        studentsAtGroups.forEach(studentsAtGroup ->
                System.out.printf("%d of students at group: %s.\n",
                        studentsAtGroup.getStudentsCount(), studentsAtGroup.getGroupName()));
    }

    public void printAllCourses() {
        List<Course> courses = courseDAO.getAll();
        if (!CollectionUtils.isEmpty(courses)) {
            courses.forEach(course -> System.out.printf("ID: %d, Course name: %s,\n   Course description: %s.\n",
                    course.getCourseID(), course.getCourseName(), course.getCourseDescription()));
        } else {
            LOGGER.info("Failed to get list of courses.");
        }
    }

    public void printAllGroups() {
        List<Group> groups = groupDAO.getAll();
        if (!CollectionUtils.isEmpty(groups)) {
            groups.forEach(group -> System.out.printf("ID: %d,  group name: %s.\n", group.getGroupID(), group.getGroupName()));
        } else {
            LOGGER.info("Failed to get list of courses.");
        }
    }
}
