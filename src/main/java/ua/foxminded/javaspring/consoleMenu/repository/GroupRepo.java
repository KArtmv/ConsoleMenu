package ua.foxminded.javaspring.consoleMenu.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.consoleMenu.dao.GroupDAO;
import ua.foxminded.javaspring.consoleMenu.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.rowmapper.CountStudentAtGroupMapper;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class GroupRepo implements GroupDAO {

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_CHECK_IS_GROUP_TABLE_EMPTY = "SELECT COUNT(*) FROM groups";
    private static final String SQL_ADD_NEW_GROUP = "insert into groups (group_name) values (?)";
    private static final String SQL_COUNT_STUDENTS_BY_GROUPS = "select group_name, count(s.student_id) as student_count"
            + "from students s" + "join groups g on s.group_id = g.group_id" + "group by g.group_name"
            + "having count(s.student_id)<=21";

    @Autowired
    public GroupRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CounterStudentsAtGroup> counterStudentsAtGroups(int count) {
        return jdbcTemplate.query(SQL_COUNT_STUDENTS_BY_GROUPS, new CountStudentAtGroupMapper(), count);
    }

    @Override
    public boolean isValidGroupID(Group group) {
        return jdbcTemplate.query(SQL_COUNT_STUDENTS_BY_GROUPS, ResultSet::next, group.getGroupID());
    }

    @Override
    public boolean addGroup(Group group) {
        return jdbcTemplate.update(SQL_ADD_NEW_GROUP, group.getGroupName()) > 0;
    }

    @Override
    public boolean isTableExist(String sqlQuery) {
        return jdbcTemplate.queryForObject(sqlQuery, Boolean.class);
    }

    @Override
    public void createGroupTable(String sqlQuery) {
        jdbcTemplate.execute(sqlQuery);
    }

    @Override
    public boolean isGroupTableEmpty() {
        return jdbcTemplate.queryForObject(SQL_CHECK_IS_GROUP_TABLE_EMPTY, Integer.class) == 0;
    }
}
