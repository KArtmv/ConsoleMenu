package ua.foxminded.javaspring.consoleMenu.repository;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.foxminded.javaspring.consoleMenu.dao.CourseDAO;
import ua.foxminded.javaspring.consoleMenu.model.Course;

@Repository
public class CourseRepo implements CourseDAO {

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_ADD_NEW_COURSE = "insert into courses (course_name, course_description) values (?, ?)";
    private static final String SQL_CHECK_IS_COURSE_EXIST = "select course_id from course where course_id=?";
    private static final String SQL_CHECK_IS_COURSE_TABLE_EMPTY = "SELECT COUNT(*) FROM courses";

    @Autowired
    public CourseRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean isValidCourseID(Course course) {
        return jdbcTemplate.query(SQL_CHECK_IS_COURSE_EXIST, ResultSet::next, course.getCourseID());
    }

    @Override
    public boolean addCourse(Course course) {
        return jdbcTemplate.update(SQL_ADD_NEW_COURSE, course.getCourseName(), course.getCourseDescription()) > 0;
    }

    @Override
    public boolean isCourseTableExist(String sqlQuery) {
        return jdbcTemplate.queryForObject(sqlQuery, Boolean.class);
    }

    @Override
    public void createCourseTable(String sqlQuery) {
        jdbcTemplate.execute(sqlQuery);
    }

    @Override
    public boolean isCourseTableEmpty() {
        return jdbcTemplate.queryForObject(SQL_CHECK_IS_COURSE_TABLE_EMPTY, Integer.class) == 0;
    }
}
