package ua.foxminded.javaspring.consoleMenu.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.consoleMenu.dao.DAO;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.rowmapper.CourseMapper;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class CourseRepo implements DAO<Course> {

    private SQLQueryOfCreateTable queryOfCreateTable;
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_ADD_NEW_COURSE = "insert into courses (course_name, course_description) values (?, ?)";
    private static final String SQL_CHECK_IS_COURSE_ID_EXIST = "select course_id from courses where course_id=?";
    private static final String SQL_CHECK_IS_COURSE_TABLE_EMPTY = "SELECT COUNT(*) FROM courses";
    private static final String SQL_GET_LIST_OF_COURSE = "select * from courses";

    @Value("${sqlQuery.IsTableExist.SQL_CHECK_IS_TABLE_EXIST}")
    private String sqlCheckIsTableExist;
    @Value("${sqlQuery.IsTableExist.COURSE_TABLE_NAME}")
    private String courseTableName;

    @Autowired
    public CourseRepo(SQLQueryOfCreateTable queryOfCreateTable, JdbcTemplate jdbcTemplate) {
        this.queryOfCreateTable = queryOfCreateTable;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean isValidItemID(Integer courseID) {
        return jdbcTemplate.query(SQL_CHECK_IS_COURSE_ID_EXIST, ResultSet::next, courseID);
    }

    @Override
    public boolean addItem(Course course) {
        return jdbcTemplate.update(SQL_ADD_NEW_COURSE, course.getCourseName(), course.getCourseDescription()) > 0;
    }

    @Override
    public boolean isTableExist() {
        return jdbcTemplate.queryForObject(String.format(sqlCheckIsTableExist, courseTableName), Boolean.class);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute(queryOfCreateTable.getCourseTable());
    }

    @Override
    public boolean isTableEmpty() {
        return jdbcTemplate.queryForObject(SQL_CHECK_IS_COURSE_TABLE_EMPTY, Integer.class) == 0;
    }

    @Override
    public List<Course> getAll() {
        return jdbcTemplate.query(SQL_GET_LIST_OF_COURSE, new CourseMapper());
    }
}
