package ua.foxminded.javaspring.consoleMenu.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.consoleMenu.dao.DAO;
import ua.foxminded.javaspring.consoleMenu.dao.GroupDAO;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.consoleMenu.model.CounterStudentsAtGroup;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.rowmapper.CountStudentAtGroupMapper;
import ua.foxminded.javaspring.consoleMenu.rowmapper.GroupMapper;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class GroupRepo implements DAO<Group>, GroupDAO {

    private SQLQueryOfCreateTable queryOfCreateTable;
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_CHECK_IS_GROUP_TABLE_EMPTY = "SELECT COUNT(*) FROM groups";
    private static final String SQL_ADD_NEW_GROUP = "insert into groups (group_name) values (?)";
    private static final String SQL_COUNT_STUDENTS_BY_GROUPS = "select group_name, count(s.student_id) as student_count"
            + "from students s" + "join groups g on s.group_id = g.group_id" + "group by g.group_name"
            + "having count(s.student_id)<=21";
    private static final String SQL_GET_GROUP_BY_ID = "select * from course where course_id=?";

    @Value("${sqlQuery.IsTableExist.SQL_CHECK_IS_TABLE_EXIST}")
    private String SQL_CHECK_IS_TABLE_EXIST;
    @Value("${sqlQuery.IsTableExist.GROUP_TABLE_NAME}")
    private String GROUP_TABLE_NAME;

    @Autowired
    public GroupRepo(SQLQueryOfCreateTable queryOfCreateTable, JdbcTemplate jdbcTemplate) {
        this.queryOfCreateTable = queryOfCreateTable;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CounterStudentsAtGroup> counterStudentsAtGroups(int count) {
        return jdbcTemplate.query(SQL_COUNT_STUDENTS_BY_GROUPS, new CountStudentAtGroupMapper(), count);
    }

    @Override
    public boolean isValidItemID(Group group) {
        return jdbcTemplate.query(SQL_COUNT_STUDENTS_BY_GROUPS, ResultSet::next, group.getGroupID());
    }

    @Override
    public boolean addItem(Group group) {
        return jdbcTemplate.update(SQL_ADD_NEW_GROUP, group.getGroupName()) > 0;
    }

    @Override
    public boolean isTableExist() {
        return jdbcTemplate.queryForObject(String.format(SQL_CHECK_IS_TABLE_EXIST, GROUP_TABLE_NAME), Boolean.class);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute(queryOfCreateTable.getGroupTable());
    }

    @Override
    public boolean isTableEmpty() {
        return jdbcTemplate.queryForObject(SQL_CHECK_IS_GROUP_TABLE_EMPTY, Integer.class) == 0;
    }

    @Override
    public boolean removeItem(Group item) {
        return false;
    }

    @Override
    public Optional<Group> getByItemID(Group group) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_GET_GROUP_BY_ID, new GroupMapper(),
                group.getGroupID()));
    }

    @Override
    public List<Group> listOfItems(Group item) {
        return null;
    }
}
