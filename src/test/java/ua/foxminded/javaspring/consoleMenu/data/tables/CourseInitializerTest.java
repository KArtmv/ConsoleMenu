package ua.foxminded.javaspring.consoleMenu.data.tables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.consoleMenu.dao.CourseDAO;
import ua.foxminded.javaspring.consoleMenu.data.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryIsTableExist;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.pattern.InitializeObject;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseInitializerTest {

    @Mock
    private CourseDAO courseDAO;

    @Mock
    private DataConduct dataConduct;

    @Mock
    private SQLQueryIsTableExist queryIsTableExist;

    @Mock
    private SQLQueryOfCreateTable queryOfCreateTable;

    @InjectMocks
    private CourseInitializer initializer;

    private InitializeObject initializeObject = new InitializeObject();

    private static final String sqlQueryTableExist = "IsTableExist";

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void initialize_shouldCreateCourseAndInsertIntoDatabaseTable_whenCourseTableExist() {
    List<Course> courses = initializeObject.coursesListInit();

        when(queryIsTableExist.queryForCourseTable()).thenReturn(sqlQueryTableExist);
        when(courseDAO.isCourseTableExist(sqlQueryTableExist)).thenReturn(true);
        when(courseDAO.isCourseTableEmpty()).thenReturn(true);
        when(dataConduct.createCourses()).thenReturn(courses);

        initializer.initialize();

        verify(queryIsTableExist).queryForCourseTable();
        verify(courseDAO).isCourseTableExist(sqlQueryTableExist);
        verify(courseDAO).isCourseTableEmpty();
        verify(dataConduct).createCourses();
    }

    @Test
    void initialize_shouldCreateTableCourseAndInsertIntoDatabaseTable_whenCourseTableNotExist() {
        String sqlQueryCreateTable = "CreateTableQuery";
        List<Course> courses = initializeObject.coursesListInit();

        when(queryIsTableExist.queryForCourseTable()).thenReturn(sqlQueryTableExist);
        when(courseDAO.isCourseTableExist(sqlQueryTableExist)).thenReturn(false);
        when(queryOfCreateTable.getCourseTable()).thenReturn(sqlQueryCreateTable);
        when(dataConduct.createCourses()).thenReturn(courses);

        initializer.initialize();

        verify(queryIsTableExist).queryForCourseTable();
        verify(courseDAO).isCourseTableExist(sqlQueryTableExist);
        verify(queryOfCreateTable).getCourseTable();
        verify(courseDAO).createCourseTable(sqlQueryCreateTable);
        verify(dataConduct).createCourses();
    }
}
