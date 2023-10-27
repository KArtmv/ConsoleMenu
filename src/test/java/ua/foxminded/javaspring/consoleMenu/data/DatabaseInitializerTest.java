package ua.foxminded.javaspring.consoleMenu.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.consoleMenu.data.tables.CourseInitializer;
import ua.foxminded.javaspring.consoleMenu.data.tables.GroupInitializer;
import ua.foxminded.javaspring.consoleMenu.data.tables.StudentInitializer;
import ua.foxminded.javaspring.consoleMenu.data.tables.StudentToCourseInitializer;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseInitializerTest {

    @Mock
    private GroupInitializer groupInitializer;

    @Mock
    private CourseInitializer courseInitializer;

    @Mock
    private StudentInitializer studentInitializer;

    @Mock
    private StudentToCourseInitializer studentToCourseInitializer;

    @InjectMocks
    private DatabaseInitializer databaseData;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        databaseData.initializeTables();
    }

    @Test
    void initializeTables_shouldRunInitializeOfGroupInitializer_whenInitializeTablesIsRun(){
        doNothing().when(groupInitializer).initialize();

        verify(groupInitializer).initialize();
    }

    @Test
    void initializeTables_shouldRunInitializeOfCourseInitializer_whenInitializeTablesIsRun(){
        doNothing().when(courseInitializer).initialize();

        verify(courseInitializer).initialize();
    }

    @Test
    void initializeTables_shouldRunInitializeOfStudentInitializer_whenInitializeTablesIsRun(){
        doNothing().when(studentInitializer).initialize();

        verify(studentInitializer).initialize();
    }

    @Test
    void initializeTables_shouldRunInitializeOfStudentToCourseInitializer_whenInitializeTablesIsRun(){
        doNothing().when(studentToCourseInitializer).initialize();

        verify(studentToCourseInitializer).initialize();
    }
}
