package ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.data.ReadResourcesFile;

@Component
public class SQLQueryOfCreateTable {

    private ReadResourcesFile readResourcesFile;

    @Autowired
    public SQLQueryOfCreateTable(ReadResourcesFile readResourcesFile) {
        this.readResourcesFile = readResourcesFile;
    }

    @Value("${sqlQuery.OfCreateTable.SQL_SCRIPT_FILE_STUDENT}")
    private String SQL_SCRIPT_STUDENT;

    @Value("${sqlQuery.OfCreateTable.SQL_SCRIPT_FILE_GROUP}")
    private String SQL_SCRIPT_GROUP;

    @Value("${sqlQuery.OfCreateTable.SQL_SCRIPT_FILE_COURSE}")
    private String SQL_SCRIPT_COURSE;

    @Value("${sqlQuery.OfCreateTable.SQL_SCRIPT_FILE_STUDENT_TO_COURSE}")
    private String SQL_SCRIPT_STUDENT_TO_COURSE;

    public String getStudentTable() {
        return getScript(SQL_SCRIPT_STUDENT);
    }

    public String getGroupTable() {
        return getScript(SQL_SCRIPT_GROUP);
    }

    public String getCourseTable() {
        return getScript(SQL_SCRIPT_COURSE);
    }

    public String getStudentToCourseTable() {
        return getScript(SQL_SCRIPT_STUDENT_TO_COURSE);
    }

    private String getScript(String filePath){
        return readResourcesFile.getScript(filePath);
    }
}