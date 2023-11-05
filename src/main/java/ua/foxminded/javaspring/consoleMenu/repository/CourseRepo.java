package ua.foxminded.javaspring.consoleMenu.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.consoleMenu.dao.DAO;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.rowmapper.CourseMapper;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepo implements DAO<Course> {

    private SQLQueryOfCreateTable queryOfCreateTable;
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_ADD_NEW_COURSE = "insert into courses (course_name, course_description) values (?, ?)";
    private static final String SQL_CHECK_IS_COURSE_EXIST = "select course_id from course where course_id=?";
    private static final String SQL_CHECK_IS_COURSE_TABLE_EMPTY = "SELECT COUNT(*) FROM courses";
    private static final String SQL_GET_COURSE_BY_ID = "select * from course where course_id=?";

    @Value("${sqlQuery.IsTableExist.SQL_CHECK_IS_TABLE_EXIST}")
    private String SQL_CHECK_IS_TABLE_EXIST;
    @Value("${sqlQuery.IsTableExist.COURSE_TABLE_NAME}")
    private String COURSE_TABLE_NAME;

    @Autowired
    public CourseRepo(SQLQueryOfCreateTable queryOfCreateTable, JdbcTemplate jdbcTemplate) {
        this.queryOfCreateTable = queryOfCreateTable;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean isValidItemID(Course course) {
        return jdbcTemplate.query(SQL_CHECK_IS_COURSE_EXIST, ResultSet::next, course.getCourseID());
    }

    @Override
    public boolean addItem(Course course) {
        return jdbcTemplate.update(SQL_ADD_NEW_COURSE, course.getCourseName(), course.getCourseDescription()) > 0;
    }

    @Override
    public boolean isTableExist() {
        return jdbcTemplate.queryForObject(String.format(SQL_CHECK_IS_TABLE_EXIST, COURSE_TABLE_NAME), Boolean.class);
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
    public boolean removeItem(Course course) {
        return false;
    }

    @Override
    public Optional<Course> getByItemID(Course course) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_GET_COURSE_BY_ID, new CourseMapper(),
                course.getCourseID()));
    }

    @Override
    public List<Course> listOfItems(Course course) {
        return null;
    }
}
