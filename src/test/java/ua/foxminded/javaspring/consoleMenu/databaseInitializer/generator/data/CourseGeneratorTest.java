package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data.CourseGenerator;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.pattern.InitializeObject;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CourseGeneratorTest {

    @Mock
    ResourcesFilesDatabaseData resourcesFiles;
    @Mock
    DataConduct dataConduct;
    @InjectMocks
    private CourseGenerator courseGenerator;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generate_shouldReturnListOfCourse_whenIsValidDataProvided() {
        List<Course> expected = new InitializeObject().coursesListInit();

        when(resourcesFiles.getCourses()).thenReturn(Arrays.asList("courseName1_courseDescription1",
                "courseName2_courseDescription2", "courseName3_courseDescription3", "courseName4_courseDescription4"));

        List<Course> result = courseGenerator.generate();
        assertThat(result).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expected);

        verify(resourcesFiles).getCourses();
        verify(dataConduct).setCourses(result);
    }
}