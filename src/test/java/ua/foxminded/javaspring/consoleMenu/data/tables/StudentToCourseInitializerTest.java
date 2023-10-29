package ua.foxminded.javaspring.consoleMenu.data.tables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.consoleMenu.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.consoleMenu.data.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryIsTableExist;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.pattern.InitializeObject;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentToCourseInitializerTest {

    @Mock
    private StudentAtCourseDAO studentAtCourseDAO;

    @Mock
    private DataConduct dataConduct;

    @Mock
    private SQLQueryIsTableExist queryIsTableExist;

    @Mock
    private SQLQueryOfCreateTable queryOfCreateTable;

    @InjectMocks
    private StudentToCourseInitializer initializer;

    private InitializeObject initializeObject = new InitializeObject();

    private static final String sqlQueryTableExist = "IsTableExist";

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void initialize_shouldCreateCourseAndInsertIntoDatabaseTable_whenStudentToCourseTableExist() {
        List<StudentAtCourse> studentAtCourses = initializeObject.studentAtCourseListInit();

        when(queryIsTableExist.queryForStudentToCourseTable()).thenReturn(sqlQueryTableExist);
        when(studentAtCourseDAO.isStudentToCourseTableExist(sqlQueryTableExist)).thenReturn(true);
        when(studentAtCourseDAO.isStudentToCourseTableEmpty()).thenReturn(true);
        when(dataConduct.createRelationStudentCourse()).thenReturn(studentAtCourses);

        initializer.initialize();

        verify(queryIsTableExist).queryForStudentToCourseTable();
        verify(studentAtCourseDAO).isStudentToCourseTableExist(sqlQueryTableExist);
        verify(studentAtCourseDAO).isStudentToCourseTableEmpty();
        verify(dataConduct).createRelationStudentCourse();
    }

    @Test
    void initialize_shouldCreateTableCourseAndInsertIntoDatabaseTable_whenStudentToCourseTableNotExist() {
        List<StudentAtCourse> studentAtCourses = initializeObject.studentAtCourseListInit();
        String sqlQueryCreateTable = "CreateTableQuery";

        when(queryIsTableExist.queryForStudentToCourseTable()).thenReturn(sqlQueryTableExist);
        when(studentAtCourseDAO.isStudentToCourseTableExist(sqlQueryTableExist)).thenReturn(false);
        when(queryOfCreateTable.getStudentToCourseTable()).thenReturn(sqlQueryCreateTable);
        when(dataConduct.createRelationStudentCourse()).thenReturn(studentAtCourses);

        initializer.initialize();

        verify(queryIsTableExist).queryForStudentToCourseTable();
        verify(studentAtCourseDAO).isStudentToCourseTableExist(sqlQueryTableExist);
        verify(queryOfCreateTable).getStudentToCourseTable();
        verify(studentAtCourseDAO).createStudentToCourseTable(sqlQueryCreateTable);
        verify(dataConduct).createRelationStudentCourse();
    }
}
