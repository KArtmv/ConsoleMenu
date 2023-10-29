package ua.foxminded.javaspring.consoleMenu.data.tables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.consoleMenu.dao.GroupDAO;
import ua.foxminded.javaspring.consoleMenu.data.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryIsTableExist;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.pattern.InitializeObject;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GroupInitializerTest {

    @Mock
    private GroupDAO groupDAO;

    @Mock
    private DataConduct dataConduct;

    @Mock
    private SQLQueryIsTableExist queryIsTableExist;

    @Mock
    private SQLQueryOfCreateTable queryOfCreateTable;

    @InjectMocks
    private GroupInitializer initializer;

    private InitializeObject initializeObject = new InitializeObject();

    private static final String sqlQueryTableExist = "IsTableExist";

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void initialize_shouldCreateCourseAndInsertIntoDatabaseTable_whenGroupTableExist() {
        List<Group> groups = initializeObject.groupsListInit();

        when(queryIsTableExist.queryForGroupTable()).thenReturn(sqlQueryTableExist);
        when(groupDAO.isTableExist(sqlQueryTableExist)).thenReturn(true);
        when(groupDAO.isGroupTableEmpty()).thenReturn(true);
        when(dataConduct.createGroups()).thenReturn(groups);

        initializer.initialize();

        verify(queryIsTableExist).queryForGroupTable();
        verify(groupDAO).isTableExist(sqlQueryTableExist);
        verify(groupDAO).isGroupTableEmpty();
        verify(dataConduct).createGroups();
    }

    @Test
    void initialize_shouldCreateTableCourseAndInsertIntoDatabaseTable_whenGroupTableNotExist() {
        List<Group> groups = initializeObject.groupsListInit();
        String sqlQueryCreateTable = "CreateTableQuery";

        when(queryIsTableExist.queryForGroupTable()).thenReturn(sqlQueryTableExist);
        when(groupDAO.isTableExist(sqlQueryTableExist)).thenReturn(false);
        when(queryOfCreateTable.getGroupTable()).thenReturn(sqlQueryCreateTable);
        when(dataConduct.createGroups()).thenReturn(groups);

        initializer.initialize();

        verify(queryIsTableExist).queryForGroupTable();
        verify(groupDAO).isTableExist(sqlQueryTableExist);
        verify(queryOfCreateTable).getGroupTable();
        verify(groupDAO).createGroupTable(sqlQueryCreateTable);
        verify(dataConduct).createGroups();
    }
}
