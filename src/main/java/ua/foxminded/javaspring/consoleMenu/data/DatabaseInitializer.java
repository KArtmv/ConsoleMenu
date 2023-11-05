package ua.foxminded.javaspring.consoleMenu.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.data.tables.TableInitializer;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.options.Menu;

import javax.annotation.PostConstruct;

@Component
public class DatabaseInitializer {
    private TableInitializer<Course> courseTableInitializer1;

    private TableInitializer<Group> groupTableInitializer;

    private TableInitializer<Student> studentTableInitializer;

    private TableInitializer<StudentAtCourse> studentAtCourseTableInitializer;

    private Menu menu;

    @Autowired
    public DatabaseInitializer(TableInitializer<Course> courseTableInitializer1, TableInitializer<Group> groupTableInitializer, TableInitializer<Student> studentTableInitializer, TableInitializer<StudentAtCourse> studentAtCourseTableInitializer, Menu menu) {
        this.courseTableInitializer1 = courseTableInitializer1;
        this.studentTableInitializer = studentTableInitializer;
        this.studentAtCourseTableInitializer = studentAtCourseTableInitializer;
        this.groupTableInitializer = groupTableInitializer;
        this.menu = menu;
    }

    @PostConstruct
    public void initializeTables() {
        groupTableInitializer.initialize();

        courseTableInitializer1.initialize();

        studentTableInitializer.initialize();

        studentAtCourseTableInitializer.initialize();

        System.out.println(menu.getOptions());
    }
}
