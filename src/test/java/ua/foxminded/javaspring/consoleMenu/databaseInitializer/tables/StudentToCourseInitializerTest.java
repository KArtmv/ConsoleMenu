package ua.foxminded.javaspring.consoleMenu.data.tables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.consoleMenu.dao.StudentAtCourseDAO;
import ua.foxminded.javaspring.consoleMenu.dao.TablesDAO;

import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data.DataGenerator;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.tables.TableInitializer;
import ua.foxminded.javaspring.consoleMenu.model.StudentAtCourse;
import ua.foxminded.javaspring.consoleMenu.pattern.InitializeObject;

import java.util.List;

import static org.mockito.Mockito.*;

class StudentToCourseInitializerTest {
    @Mock
    private TablesDAO<StudentAtCourse> dao;
    @Mock
    private DataGenerator<StudentAtCourse> generateItems;

    @InjectMocks
    private TableInitializer<StudentAtCourse> initializer;

    private InitializeObject initializeObject = new InitializeObject();

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void initialize_shouldCreateCourseAndInsertIntoDatabaseTable_whenStudentToCourseTableExist() {
        List<StudentAtCourse> studentAtCourses = initializeObject.studentAtCourseListInit();

        when(dao.isTableExist()).thenReturn(true);
        when(dao.isTableEmpty()).thenReturn(true);
        when(generateItems.generate()).thenReturn(studentAtCourses);

        initializer.initialize();

        verify(dao).isTableExist();
        verify(dao).isTableEmpty();
        verify(generateItems).generate();
        verify(dao, times(studentAtCourses.size())).addItem(any(StudentAtCourse.class));
    }

    @Test
    void initialize_shouldCreateTableCourseAndInsertIntoDatabaseTable_whenStudentToCourseTableNotExist() {
        List<StudentAtCourse> studentAtCourses = initializeObject.studentAtCourseListInit();

        when(dao.isTableExist()).thenReturn(false);
        when(generateItems.generate()).thenReturn(studentAtCourses);

        initializer.initialize();

        verify(dao).isTableExist();
        verify(generateItems).generate();
        verify(dao, times(studentAtCourses.size())).addItem(any(StudentAtCourse.class));
    }
}
