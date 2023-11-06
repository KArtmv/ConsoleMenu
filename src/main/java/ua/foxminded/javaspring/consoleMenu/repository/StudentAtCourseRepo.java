package ua.foxminded.javaspring.consoleMenu.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.consoleMenu.dao.DAO;
import ua.foxminded.javaspring.consoleMenu.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.rowmapper.StudentAtCourseMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentAtCourseRepo implements DAO<StudentAtCourse>, StudentAtCourseDAO {

    private SQLQueryOfCreateTable queryOfCreateTable;
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ALL_STUDENT_FROM_COURSE = "select c.course_name, c.course_description, s.first_name, s.last_name"
            + "from studenttocourse sc" + "join students s on s.student_id = sc.student_id"
            + "join courses c on c.course_id = sc.course_id" + "where c.course_id=1";
    private static final String SQL_ADD_STUDENT_TO_COURSE = "insert into studenttocourse (student_id, course_id) values (?, ?)";
    private static final String SQL_REMOVE_STUDENT_FROM_COURSE = "delete from studentatcourse where enrollment_id=?";
    private static final String SQL_REMOVE_STUDENT_FROM_ALL_THEIR_COURSES = "delete from studentatcourse where student_id=?";
    private static final String SQL_CHECK_IS_STUDENT_TO_COURSE_TABLE_EMPTY = "SELECT COUNT(*) FROM studenttocourse";

    @Value("${sqlQuery.IsTableExist.SQL_CHECK_IS_TABLE_EXIST}")
    private String SQL_CHECK_IS_TABLE_EXIST;
    @Value("${sqlQuery.IsTableExist.STUDENT_TO_COURSE_TABLE_NAME}")
    private String STUDENT_TO_COURSE_TABLE_NAME;

    @Autowired
    public StudentAtCourseRepo(SQLQueryOfCreateTable queryOfCreateTable, JdbcTemplate jdbcTemplate) {
        this.queryOfCreateTable = queryOfCreateTable;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<StudentAtCourse> allStudentsFromCourse(Course course) {
        return jdbcTemplate.query(SQL_GET_ALL_STUDENT_FROM_COURSE, new StudentAtCourseMapper(), course.getCourseID());
    }

    @Override
    public boolean addItem(StudentAtCourse studentAtCourse) {
        return jdbcTemplate.update(SQL_ADD_STUDENT_TO_COURSE, studentAtCourse.getStudent().getStudentID(), studentAtCourse.getCourse().getCourseID()) > 0;
    }

    @Override
    public boolean isValidItemID(StudentAtCourse item) {
        return false;
    }

    @Override
    public boolean removeItem(StudentAtCourse studentAtCourse) {
        return jdbcTemplate.update(SQL_REMOVE_STUDENT_FROM_COURSE, studentAtCourse.getEnrollmentID()) > 0;
    }

    @Override
    public boolean removeStudentFromAllTheirCourses(Student student) {
        return jdbcTemplate.update(SQL_REMOVE_STUDENT_FROM_ALL_THEIR_COURSES, student.getStudentID()) > 0;
    }


    @Override
    public boolean isTableExist() {
        return jdbcTemplate.queryForObject(String.format(SQL_CHECK_IS_TABLE_EXIST, STUDENT_TO_COURSE_TABLE_NAME), Boolean.class);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute(queryOfCreateTable.getStudentToCourseTable());
    }

    @Override
    public boolean isTableEmpty() {
        return jdbcTemplate.queryForObject(SQL_CHECK_IS_STUDENT_TO_COURSE_TABLE_EMPTY, Integer.class) == 0;
    }

    @Override
    public Optional<StudentAtCourse> getByItemID(StudentAtCourse item) {
        return Optional.empty();
    }

    @Override
    public List<StudentAtCourse> listOfItems(StudentAtCourse item) {
        return null;
    }
}
