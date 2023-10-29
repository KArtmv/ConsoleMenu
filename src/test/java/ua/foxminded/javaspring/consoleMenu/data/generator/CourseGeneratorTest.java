package ua.foxminded.javaspring.consoleMenu.data.generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.consoleMenu.data.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Course;
import ua.foxminded.javaspring.consoleMenu.pattern.InitializeObject;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseGeneratorTest {

    @Mock
    ResourcesFilesDatabaseData resourcesFiles;

    @InjectMocks
    private CourseGenerator courseGenerator;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generate_shouldReturnListOfCourse_whenIsValidDataProvided() {
        InitializeObject initializeObject = new InitializeObject();
        List<Course> expected = initializeObject.coursesListInit();

        when(resourcesFiles.getCourses()).thenReturn(Arrays.asList("courseName_courseDescription",
                "courseName_courseDescription", "courseName_courseDescription"));

        List<Course> result = courseGenerator.generate();

        assertThat(result).usingRecursiveComparison().isEqualTo(expected);

        verify(resourcesFiles).getCourses();
    }
}