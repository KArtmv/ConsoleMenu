package ua.foxminded.javaspring.consoleMenu.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.consoleMenu.dao.DAO;
import ua.foxminded.javaspring.consoleMenu.dao.StudentDAO;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.rowmapper.StudentAtCourseMapper;
import ua.foxminded.javaspring.consoleMenu.rowmapper.StudentMapper;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepo implements DAO<Student>, StudentDAO {

    private SQLQueryOfCreateTable queryOfCreateTable;
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_ADD_NEW_STUDENT = "insert into students (first_name, last_name, group_id) values (?, ?, ?)";
    private static final String SQL_GET_STUDENT_BY_ID = "select * from student where student_id=?";
    private static final String SQL_DELETE_STUDENT_BY_ID = "delete from student where student_id=?";
    private static final String SQL_GET_LIST_COURSES_OF_STUDENT = "select"
            + "sc.enrollment_id, s.first_name, s.last_name, c.course_name, c.course_description"
            + "from student s join studentatcourse sc on s.student_id = sc.student_id"
            + "join course c on sc.course_id = c.course_id" + "where student_id=?";
    private static final String SQL_CHECK_IS_STUDENT_EXIST = "select student_id from student where student_id=?";
    private static final String SQL_CHECK_IS_STUDEN_TABLE_EMPTY = "SELECT COUNT(*) FROM students";

    @Value("${sqlQuery.IsTableExist.SQL_CHECK_IS_TABLE_EXIST}")
    private String SQL_CHECK_IS_TABLE_EXIST;
    @Value("${sqlQuery.IsTableExist.STUDENT_TABLE_NAME}")
    private String STUDENT_TABLE_NAME;

    @Autowired
    public StudentRepo(SQLQueryOfCreateTable queryOfCreateTable, JdbcTemplate jdbcTemplate) {
        this.queryOfCreateTable = queryOfCreateTable;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Student> getByItemID(Student student) {
//        Student studentResult = jdbcTemplate.queryForObject(SQL_GET_STUDENT_BY_ID, new StudentMapper(),
//                student.getStudentID());
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_GET_STUDENT_BY_ID, new StudentMapper(),
                student.getStudentID()));
    }

    @Override
    public List<Student> listOfItems(Student item) {
        return null;
    }

    @Override
    public boolean removeItem(Student student) {
        return jdbcTemplate.update(SQL_DELETE_STUDENT_BY_ID, student.getStudentID()) > 0;
    }

    @Override
    public List<StudentAtCourse> studentCourses(Student student) {
        return jdbcTemplate.query(SQL_GET_LIST_COURSES_OF_STUDENT, new StudentAtCourseMapper(), student.getStudentID());
    }

    @Override
    public boolean addItem(Student student) {
        return jdbcTemplate.update(SQL_ADD_NEW_STUDENT, student.getFirstName(), student.getLastName(),
                student.getGroupID()) > 0;
    }

    @Override
    public boolean isValidItemID(Student student) {
        return jdbcTemplate.query(SQL_CHECK_IS_STUDENT_EXIST, ResultSet::next, student.getStudentID());
    }

    @Override
    public boolean isTableExist() {
        return jdbcTemplate.queryForObject(String.format(SQL_CHECK_IS_TABLE_EXIST, STUDENT_TABLE_NAME), Boolean.class);
    }

    public void createTable() {
        jdbcTemplate.execute(queryOfCreateTable.getStudentTable());
    }

    @Override
    public boolean isTableEmpty() {
        return jdbcTemplate.queryForObject(SQL_CHECK_IS_STUDEN_TABLE_EMPTY, Integer.class) == 0;
    }
}
