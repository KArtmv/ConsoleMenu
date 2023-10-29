package ua.foxminded.javaspring.consoleMenu.data.generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.consoleMenu.data.RandomNumber;
import ua.foxminded.javaspring.consoleMenu.data.generator.sourceData.CountConfig;
import ua.foxminded.javaspring.consoleMenu.data.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StudentGeneratorTest {
    @Mock
    RandomNumber randomNumber;

    @Mock
    ResourcesFilesDatabaseData resourcesFiles;

    @Mock
    CountConfig countConfig;

    @InjectMocks
    private StudentGenerator studentGenerator;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generate_shouldReturnListOfStudents_whenIsOk() {
        List<String> firstNames = Arrays.asList("firstName1", "firstName2", "firstName3");
        List<String> lastNames = Arrays.asList("lastName1", "lastName2", "lastName3", "lastName4");

        int countFirstNames = firstNames.size();
        int countLastNames = lastNames.size();

        List<Group> groups = new ArrayList<>();
        groups.add(new Group(1L, "group1"));
        groups.add(new Group(2L, "group2"));
        groups.add(new Group(3L, "group3"));
        groups.add(new Group(4L, "group4"));
        groups.add(new Group(5L, "group5"));

        when(resourcesFiles.getFirstNames()).thenReturn(firstNames);
        when(resourcesFiles.getLastNames()).thenReturn(lastNames);

        when(countConfig.getMaxCountOfStudents()).thenReturn(3);

        when(randomNumber.generateBetweenOneAndInputNumber(countFirstNames)).thenReturn(1, 2, 3);
        when(randomNumber.generateBetweenOneAndInputNumber(countLastNames)).thenReturn(1, 2, 3);
        when(randomNumber.generateBetweenOneAndInputNumber(groups.size())).thenReturn(3, 2, 1);

        List<Student> result = studentGenerator.generate(groups);

        for (Student student : result) {
            assertThat(student.getStudentID() > 0 && student.getStudentID() < 4).isTrue();
            assertThat(student.getFirstName()).isNotEmpty();
            assertThat(student.getLastName()).isNotEmpty();
            assertThat(student.getGroupID() > 0 && student.getGroupID() <= 5).isTrue();
        }

        verify(randomNumber, times(3)).generateBetweenOneAndInputNumber(countFirstNames);
        verify(randomNumber, times(3)).generateBetweenOneAndInputNumber(countLastNames);
        verify(randomNumber, times(3)).generateBetweenOneAndInputNumber(groups.size());
    }
}