package ua.foxminded.javaspring.consoleMenu.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.consoleMenu.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.rowmapper.StudentAtCourseMapper;

@Repository
public class StudentAtCourseRepo implements StudentAtCourseDAO {

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ALL_STUDENT_FROM_COURSE = "select c.course_name, c.course_description, s.first_name, s.last_name"
            + "from studenttocourse sc" + "join students s on s.student_id = sc.student_id"
            + "join courses c on c.course_id = sc.course_id" + "where c.course_id=1";
    private static final String SQL_ADD_STUDENT_TO_COURSE = "insert into studenttocourse (student_id, course_id) values (?, ?)";
    private static final String SQL_REMOVE_STUDENT_FROM_COURSE = "delete from studentatcourse where enrollment_id=?";
    private static final String SQL_REMOVE_STUDENT_FROM_ALL_THEIR_COURSES = "delete from studentatcourse where student_id=?";
    private static final String SQL_CHECK_IS_STUDENT_TO_COURSE_TABLE_EMPTY = "SELECT COUNT(*) FROM studenttocourse";

    @Autowired
    public StudentAtCourseRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<StudentAtCourse> allStudentsFromCourse(Course course) {
        return jdbcTemplate.query(SQL_GET_ALL_STUDENT_FROM_COURSE, new StudentAtCourseMapper(), course.getCourseID());
    }

    @Override
    public boolean addStudentToCourse(Student student, Course course) {
        return jdbcTemplate.update(SQL_ADD_STUDENT_TO_COURSE, student.getStudentID(), course.getCourseID()) > 0;
    }

    @Override
    public boolean removeStudentFromCourse(StudentAtCourse studentAtCourse) {
        return jdbcTemplate.update(SQL_REMOVE_STUDENT_FROM_COURSE, studentAtCourse.getEnrollmentID()) > 0;
    }

    @Override
    public boolean removeStudentFromAllTheirCourses(Student student) {
        return jdbcTemplate.update(SQL_REMOVE_STUDENT_FROM_ALL_THEIR_COURSES, student.getStudentID()) > 0;
    }

    @Override
    public boolean isStudentToCourseTableExist(String sqlQuery) {
        return jdbcTemplate.queryForObject(sqlQuery, Boolean.class);
    }

    @Override
    public void createStudentToCourseTable(String sqlQuery) {
        jdbcTemplate.execute(sqlQuery);
    }

    @Override
    public boolean isStudentToCourseTableEmpty() {
        return jdbcTemplate.queryForObject(SQL_CHECK_IS_STUDENT_TO_COURSE_TABLE_EMPTY, Integer.class) == 0;
    }

}
