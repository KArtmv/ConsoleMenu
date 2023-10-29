package ua.foxminded.javaspring.consoleMenu.data.tables;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.dao.CourseDAO;
import ua.foxminded.javaspring.consoleMenu.data.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryIsTableExist;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.consoleMenu.model.Course;

import java.util.List;

public class CourseInitializer {

    private CourseDAO courseDAO;

    private DataConduct dataConduct;


    private SQLQueryIsTableExist queryIsTableExist;

    private SQLQueryOfCreateTable queryOfCreateTable;

    private List<Course> courses;

    @Autowired
    public CourseInitializer(CourseDAO courseDAO, DataConduct dataConduct, SQLQueryIsTableExist queryIsTableExist, SQLQueryOfCreateTable queryOfCreateTable) {
        this.courseDAO = courseDAO;
        this.dataConduct = dataConduct;
        this.queryIsTableExist = queryIsTableExist;
        this.queryOfCreateTable = queryOfCreateTable;
    }

    public void initialize() {
        if (courseDAO.isCourseTableExist(queryIsTableExist.queryForCourseTable())) {
            populateIfEmpty();
        } else {
            courseDAO.createCourseTable(queryOfCreateTable.getCourseTable());
            populateTable();
        }
    }

    private void populateIfEmpty() {
        if (courseDAO.isCourseTableEmpty()) {
            populateTable();
        }
    }

    private void populateTable() {
        generateData();
        courses.forEach(courseDAO::addCourse);
    }

    private void generateData() {
        courses = dataConduct.createCourses();
    }
}
