package ua.foxminded.javaspring.consoleMenu.data.tables;

import ua.foxminded.javaspring.consoleMenu.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.consoleMenu.data.DataConduct;
import ua.foxminded.javaspring.consoleMenu.data.ReadResourcesFile;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryIsTableExist;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

import java.util.List;

public class StudentToCourseInitializer {

    private StudentAtCourseDAO studentAtCourseDAO;

    private DataConduct dataConduct;

    private ReadResourcesFile readResourcesFile;

    private SQLQueryIsTableExist queryIsTableExist;

    private SQLQueryOfCreateTable queryOfCreateTable;

    private List<StudentAtCourse> studentAtCourses;

    public StudentToCourseInitializer(StudentAtCourseDAO studentAtCourseDAO, DataConduct dataConduct,
                                      ReadResourcesFile readResourcesFile, SQLQueryIsTableExist queryIsTableExist, SQLQueryOfCreateTable queryOfCreateTable) {
        this.studentAtCourseDAO = studentAtCourseDAO;
        this.dataConduct = dataConduct;
        this.readResourcesFile = readResourcesFile;
        this.queryIsTableExist = queryIsTableExist;
        this.queryOfCreateTable = queryOfCreateTable;
    }

    public void initialize() {
        if (studentAtCourseDAO.isStudentToCourseTableExist(queryIsTableExist.queryForStudentToCourseTable())) {
            checkIsTableEmptyAndPopulate();
        } else {
            studentAtCourseDAO
                    .createStudentToCourseTable(readResourcesFile.getScript(queryOfCreateTable.getStudentToCourseFilePath()));
            populateTable();
        }
    }

    private void checkIsTableEmptyAndPopulate() {
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
