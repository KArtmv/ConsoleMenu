package ua.foxminded.javaspring.consoleMenu.data;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.data.tables.CourseInitializer;
import ua.foxminded.javaspring.consoleMenu.data.tables.GroupInitializer;
import ua.foxminded.javaspring.consoleMenu.data.tables.StudentInitializer;
import ua.foxminded.javaspring.consoleMenu.data.tables.StudentToCourseInitializer;

@Component
public class DatabaseInitializer {
    private GroupInitializer groupInitializer;

    private CourseInitializer courseInitializer;

    private StudentInitializer studentInitializer;

    private StudentToCourseInitializer studentToCourseInitializer;

    @Autowired
    public DatabaseInitializer(GroupInitializer groupInitializer, CourseInitializer courseInitializer,
                               StudentInitializer studentInitializer, StudentToCourseInitializer studentToCourseInitializer) {
        this.groupInitializer = groupInitializer;
        this.courseInitializer = courseInitializer;
        this.studentInitializer = studentInitializer;
        this.studentToCourseInitializer = studentToCourseInitializer;
    }

    @PostConstruct
    public void initializeTables() {
        groupInitializer.initialize();

        courseInitializer.initialize();

        studentInitializer.initialize();

        studentToCourseInitializer.initialize();
    }
}
