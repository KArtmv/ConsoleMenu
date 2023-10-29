package ua.foxminded.javaspring.consoleMenu.data.generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.consoleMenu.data.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.pattern.InitializeObject;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GroupGeneratorTest {

    @Mock
    ResourcesFilesDatabaseData resourcesFiles;

    @InjectMocks
    private GroupGenerator groupGenerator;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generate_shouldReturnListOfGroup_whenProvidedDataIsValid() {
        InitializeObject initializeObject = new InitializeObject();
        List<Group> expect = initializeObject.groupsListInit();

        when(resourcesFiles.getGroups()).thenReturn(Arrays.asList("groupName", "groupName", "groupName"));

        List<Group> result = groupGenerator.generate();

        assertThat(result).usingRecursiveComparison().isEqualTo(expect);

        verify(resourcesFiles).getGroups();
    }
}
