package ua.foxminded.javaspring.consoleMenu.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.data.tables.TableInitializer;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.Menu;
import ua.foxminded.javaspring.consoleMenu.options.controller.groupOption.OutputStudentsAtGroupByCount;
import ua.foxminded.javaspring.consoleMenu.options.input.TestCheck;

import javax.annotation.PostConstruct;

@Component
public class DatabaseInitializer {
    private TableInitializer<Course> courseTableInitializer1;

    private TableInitializer<Group> groupTableInitializer;

    private TableInitializer<Student> studentTableInitializer;

    private TableInitializer<StudentAtCourse> studentAtCourseTableInitializer;

    private Menu menu;
    private OutputStudentsAtGroupByCount atGroupByCount;
    private TestCheck testCheck;

    @Autowired
    public DatabaseInitializer(TableInitializer<Course> courseTableInitializer1, TableInitializer<Group> groupTableInitializer, TableInitializer<Student> studentTableInitializer, TableInitializer<StudentAtCourse> studentAtCourseTableInitializer, Menu menu, OutputStudentsAtGroupByCount atGroupByCount, TestCheck testCheck) {
        this.courseTableInitializer1 = courseTableInitializer1;
        this.groupTableInitializer = groupTableInitializer;
        this.studentTableInitializer = studentTableInitializer;
        this.studentAtCourseTableInitializer = studentAtCourseTableInitializer;
        this.menu = menu;
        this.atGroupByCount = atGroupByCount;
        this.testCheck = testCheck;
    }

    @PostConstruct
    public void initializeTables() {
        groupTableInitializer.initialize();

        courseTableInitializer1.initialize();

        studentTableInitializer.initialize();

        studentAtCourseTableInitializer.initialize();

        System.out.println(menu.getOptions());

//        atGroupByCount.groupsByCount();

        testCheck.testCheck();
    }
}
