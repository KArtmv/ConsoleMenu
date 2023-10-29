package ua.foxminded.javaspring.consoleMenu.data.tables;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.dao.StudentDAO;
import ua.foxminded.javaspring.consoleMenu.data.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryIsTableExist;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.consoleMenu.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentInitializer {

    private StudentDAO studentDAO;

    private DataConduct dataConduct;

    private SQLQueryIsTableExist queryIsTableExist;

    private SQLQueryOfCreateTable queryOfCreateTable;

    private List<Student> students = new ArrayList<>();

    @Autowired
    public StudentInitializer(StudentDAO studentDAO, DataConduct dataConduct, SQLQueryIsTableExist queryIsTableExist, SQLQueryOfCreateTable queryOfCreateTable) {
        this.studentDAO = studentDAO;
        this.dataConduct = dataConduct;
        this.queryIsTableExist = queryIsTableExist;
        this.queryOfCreateTable = queryOfCreateTable;
    }

    public void initialize() {
        if (studentDAO.isTableExist(queryIsTableExist.queryForStudentTable())) {
            populateIfEmpty();
        } else {
            studentDAO.createStudentTable(queryOfCreateTable.getStudentTable());
            populateTable();
        }
    }

    private void populateIfEmpty() {
        if (studentDAO.isTableEmpty()) {
            populateTable();
        }
    }

    private void populateTable() {
        generateStudentsData();
        students.forEach(studentDAO::addStudent);
    }

    private void generateStudentsData() {
        students = dataConduct.createStudents();
    }
}
