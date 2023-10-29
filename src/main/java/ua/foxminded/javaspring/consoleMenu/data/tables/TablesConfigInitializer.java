package ua.foxminded.javaspring.consoleMenu.data.tables;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.dao.CourseDAO;
import ua.foxminded.javaspring.consoleMenu.dao.GroupDAO;
import ua.foxminded.javaspring.consoleMenu.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.consoleMenu.dao.StudentDAO;
import ua.foxminded.javaspring.consoleMenu.data.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryIsTableExist;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryOfCreateTable;

@Component
public class TablesConfigInitializer {
    @Bean
    public GroupInitializer groupInitializer(GroupDAO groupDAO, DataConduct dataConduct, SQLQueryIsTableExist queryIsTableExist, SQLQueryOfCreateTable queryOfCreateTable) {
        return new GroupInitializer(groupDAO, dataConduct, queryIsTableExist, queryOfCreateTable);
    }

    @Bean
    public CourseInitializer courseInitializer(CourseDAO courseDAO, DataConduct dataConduct, SQLQueryIsTableExist queryIsTableExist, SQLQueryOfCreateTable queryOfCreateTable) {
        return new CourseInitializer(courseDAO, dataConduct, queryIsTableExist, queryOfCreateTable);
    }

    @Bean
    public StudentInitializer studentInitializer(StudentDAO studentDAO, DataConduct dataConduct, SQLQueryIsTableExist queryIsTableExist, SQLQueryOfCreateTable queryOfCreateTable) {
        return new StudentInitializer(studentDAO, dataConduct, queryIsTableExist, queryOfCreateTable);
    }

    @Bean
    public StudentToCourseInitializer studentToCourseInitializer(StudentAtCourseDAO studentAtCourseDAO, DataConduct dataConduct, SQLQueryIsTableExist queryIsTableExist, SQLQueryOfCreateTable queryOfCreateTable) {
        return new StudentToCourseInitializer(studentAtCourseDAO, dataConduct, queryIsTableExist, queryOfCreateTable);
    }
}
