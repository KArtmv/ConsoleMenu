package ua.foxminded.javaspring.consoleMenu.data.tables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.consoleMenu.dao.StudentDAO;
import ua.foxminded.javaspring.consoleMenu.data.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryIsTableExist;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.pattern.InitializeObject;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentInitializerTest {

    @Mock
    private StudentDAO studentDAO;

    @Mock
    private DataConduct dataConduct;

    @Mock
    private SQLQueryIsTableExist queryIsTableExist;

    @Mock
    private SQLQueryOfCreateTable queryOfCreateTable;

    @InjectMocks
    private StudentInitializer initializer;

    private InitializeObject initializeObject = new InitializeObject();

    private static final String sqlQueryTableExist = "IsTableExist";

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void initialize_shouldCreateCourseAndInsertIntoDatabaseTable_whenGroupTableExist() {
        List<Student> students = initializeObject.studentsListInit();

        when(queryIsTableExist.queryForStudentTable()).thenReturn(sqlQueryTableExist);
        when(studentDAO.isTableExist(sqlQueryTableExist)).thenReturn(true);
        when(studentDAO.isTableEmpty()).thenReturn(true);
        when(dataConduct.createStudents()).thenReturn(students);

        initializer.initialize();

        verify(queryIsTableExist).queryForStudentTable();
        verify(studentDAO).isTableExist(sqlQueryTableExist);
        verify(studentDAO).isTableEmpty();
        verify(dataConduct).createStudents();
    }

    @Test
    void initialize_shouldCreateTableCourseAndInsertIntoDatabaseTable_whenGroupTableNotExist() {
        List<Student> students = initializeObject.studentsListInit();
        String sqlQueryCreateTable = "CreateTableQuery";

        when(queryIsTableExist.queryForStudentTable()).thenReturn(sqlQueryTableExist);
        when(studentDAO.isTableExist(sqlQueryTableExist)).thenReturn(false);
        when(queryOfCreateTable.getStudentTable()).thenReturn(sqlQueryCreateTable);
        when(dataConduct.createStudents()).thenReturn(students);

        initializer.initialize();

        verify(queryIsTableExist).queryForStudentTable();
        verify(studentDAO).isTableExist(sqlQueryTableExist);
        verify(queryOfCreateTable).getStudentTable();
        verify(studentDAO).createStudentTable(sqlQueryCreateTable);
        verify(dataConduct).createStudents();
    }
}
