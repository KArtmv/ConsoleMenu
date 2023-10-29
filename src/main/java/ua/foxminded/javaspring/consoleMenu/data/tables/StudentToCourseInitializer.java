package ua.foxminded.javaspring.consoleMenu.data.tables;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.consoleMenu.data.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryIsTableExist;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

import java.util.List;

public class StudentToCourseInitializer {

    private StudentAtCourseDAO studentAtCourseDAO;

    private DataConduct dataConduct;

    private SQLQueryIsTableExist queryIsTableExist;

    private SQLQueryOfCreateTable queryOfCreateTable;

    private List<StudentAtCourse> studentAtCourses;

    @Autowired
    public StudentToCourseInitializer(StudentAtCourseDAO studentAtCourseDAO, DataConduct dataConduct,
                                      SQLQueryIsTableExist queryIsTableExist, SQLQueryOfCreateTable queryOfCreateTable) {
        this.studentAtCourseDAO = studentAtCourseDAO;
        this.dataConduct = dataConduct;
        this.queryIsTableExist = queryIsTableExist;
        this.queryOfCreateTable = queryOfCreateTable;
    }

    public void initialize() {
        if (studentAtCourseDAO.isStudentToCourseTableExist(queryIsTableExist.queryForStudentToCourseTable())) {
            populateIfEmpty();
        } else {
            studentAtCourseDAO
                    .createStudentToCourseTable(queryOfCreateTable.getStudentToCourseTable());
            populateTable();
        }
    }

    private void populateIfEmpty() {
        if (studentAtCourseDAO.isStudentToCourseTableEmpty()) {
            populateTable();
        }
    }

    private void populateTable() {
        generateStudentAtCoursesData();
        studentAtCourses.forEach(t -> studentAtCourseDAO.addStudentToCourse(t.getStudent(), t.getCourse()));
    }

    private void generateStudentAtCoursesData() {
        studentAtCourses = dataConduct.createRelationStudentCourse();
    }
}
