package ua.foxminded.javaspring.consoleMenu.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.foxminded.javaspring.consoleMenu.model.Student;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Student(rs.getLong("student_id"), rs.getString("first_name"), rs.getString("last_name"),
                rs.getString("group_name"));
    }
}
