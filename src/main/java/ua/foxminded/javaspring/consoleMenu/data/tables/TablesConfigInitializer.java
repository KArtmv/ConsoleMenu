package ua.foxminded.javaspring.consoleMenu.data.tables;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.dao.DAO;
import ua.foxminded.javaspring.consoleMenu.data.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

@Component
public class TablesConfigInitializer {

    @Bean
    public TableInitializer<Course> courseTableInitializer(DAO<Course> dao, DataConduct dataConduct) {
        return new TableInitializer<>(dao, dataConduct, Course.class);
    }

    @Bean
    public TableInitializer<Group> groupTableInitializer(DAO<Group> dao, DataConduct dataConduct) {
        return new TableInitializer<>(dao, dataConduct, Group.class);
    }

    @Bean
    public TableInitializer<Student> studebtTableInitializer(DAO<Student> dao, DataConduct dataConduct) {
        return new TableInitializer<>(dao, dataConduct, Student.class);
    }

    @Bean
    public TableInitializer<StudentAtCourse> studentAtCourseTableInitializer(DAO<StudentAtCourse> dao, DataConduct dataConduct) {
        return new TableInitializer<>(dao, dataConduct, StudentAtCourse.class);
    }
}
