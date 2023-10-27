package ua.foxminded.javaspring.consoleMenu.data.tables;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import ua.foxminded.javaspring.consoleMenu.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.consoleMenu.data.DataConduct;
import ua.foxminded.javaspring.consoleMenu.data.ReadResourcesFile;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryIsTableExist;
import ua.foxminded.javaspring.consoleMenu.data.tables.sqlScripts.SQLQueryOfCreateTable;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.model.Student;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;

@RunWith(MockitoJUnitRunner.class)
public class StudentToCourseInitializerTest {

    @Mock
    private StudentAtCourseDAO studentAtCourseDAO;

    @Mock
    private DataConduct dataConduct;

    @Mock
    private ReadResourcesFile readResourcesFile;

    @Mock
    private SQLQueryIsTableExist queryIsTableExist;

    @Mock
    private SQLQueryOfCreateTable queryOfCreateTable;

    @InjectMocks
    private StudentToCourseInitializer initializer;

    private String sqlQueryTableExist;

    private List<StudentAtCourse> studentAtCourses;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        StudentAtCourse studentAtCourse = new StudentAtCourse(new Student("firsName", "lastName"),
                new Course("course", "discription"));
        studentAtCourses = Arrays.asList(studentAtCourse, studentAtCourse, studentAtCourse);
        sqlQueryTableExist = "IsTableExist";
    }

    @Test
    void initializeStudentToCourseTableAndData_shouldCreateCourseAndInsertIntoDatabaseTable_whenStudentToCourseTableExist() {
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
    void initializeStudentToCourseTableAndData_shouldCreateTableCourseAndInsertIntoDatabaseTable_whenStudentToCourseTableNotExist() {
        String filePath = "table/studentToCourse.txt";
        String sqlQueryCreateTable = "CreateTableQuery";

        when(queryIsTableExist.queryForStudentToCourseTable()).thenReturn(sqlQueryTableExist);
        when(studentAtCourseDAO.isStudentToCourseTableExist(sqlQueryTableExist)).thenReturn(false);
        when(queryOfCreateTable.getStudentToCourseFilePath()).thenReturn(filePath);
        when(readResourcesFile.getScript(filePath)).thenReturn(sqlQueryCreateTable);
        when(dataConduct.createRelationStudentCourse()).thenReturn(studentAtCourses);

        initializer.initialize();

        verify(queryIsTableExist).queryForStudentToCourseTable();
        verify(studentAtCourseDAO).isStudentToCourseTableExist(sqlQueryTableExist);
        verify(queryOfCreateTable).getStudentToCourseFilePath();
        verify(readResourcesFile).getScript(filePath);
        verify(dataConduct).createRelationStudentCourse();
    }
}
