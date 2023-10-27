package ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SQLQueryIsTableExist {
    @Value("${sqlQueryIsTableExist.SQL_CHECK_IS_TABLE_EXIST}")
    private String SQL_CHECK_IS_TABLE_EXIST;

    @Value("${sqlQueryIsTableExist.STUDENT_TABLE_NAME}")
    private String STUDENT_TABLE_NAME;

    @Value("${sqlQueryIsTableExist.GROUP_TABLE_NAME}")
    private String GROUP_TABLE_NAME;

    @Value("${sqlQueryIsTableExist.COURSE_TABLE_NAME}")
    private String COURSE_TABLE_NAME;

    @Value("${sqlQueryIsTableExist.STUDENT_TO_COURSE_TABLE_NAME}")
    private String STUDENT_TO_COURSE_TABLE_NAME;

    public String queryForStudentTable() {
        return String.format(SQL_CHECK_IS_TABLE_EXIST, STUDENT_TABLE_NAME);
    }

    public String queryForCourseTable() {
        return String.format(SQL_CHECK_IS_TABLE_EXIST, COURSE_TABLE_NAME);
    }

    public String queryForGroupTable() {
        return String.format(SQL_CHECK_IS_TABLE_EXIST, GROUP_TABLE_NAME);
    }

    public String queryForStudentToCourseTable() {
        return String.format(SQL_CHECK_IS_TABLE_EXIST, STUDENT_TO_COURSE_TABLE_NAME);
    }
}
