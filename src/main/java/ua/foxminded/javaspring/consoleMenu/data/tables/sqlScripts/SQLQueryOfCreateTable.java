package ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SQLQueryOfCreateTable {

    @Value("${sqlQueryOfCreateTable.SQL_SCRIPT_FILE_STUDENT}")
    private String SQL_SCRIPT_FILE_STUDENT;

    @Value("${sqlQueryOfCreateTable.SQL_SCRIPT_FILE_GROUP}")
    private String SQL_SCRIPT_FILE_GROUP;

    @Value("${sqlQueryOfCreateTable.SQL_SCRIPT_FILE_COURSE}")
    private String SQL_SCRIPT_FILE_COURSE;

    @Value("${sqlQueryOfCreateTable.SQL_SCRIPT_FILE_STUDENT_TO_COURSE}")
    private String SQL_SCRIPT_FILE_STUDENT_TO_COURSE;

    public String getStudentFilePath() {
        return SQL_SCRIPT_FILE_STUDENT;
    }

    public String getGroupFilePath() {
        return SQL_SCRIPT_FILE_GROUP;
    }

    public String getCourseFilePath() {
        return SQL_SCRIPT_FILE_COURSE;
    }

    public String getStudentToCourseFilePath() {
        return SQL_SCRIPT_FILE_STUDENT_TO_COURSE;
    }
}
