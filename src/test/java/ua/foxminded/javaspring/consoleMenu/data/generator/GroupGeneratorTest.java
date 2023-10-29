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

import java.util.ArrayList;
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
        List<Group> expect = new ArrayList<>();
        expect.add(new Group(1L, "test"));
        expect.add(new Group(2L, "test"));
        expect.add(new Group(3L, "test"));

        when(resourcesFiles.getGroups()).thenReturn(Arrays.asList("test", "test", "test"));

        List<Group> result = groupGenerator.generate();

        assertThat(result).usingRecursiveComparison().isEqualTo(expect);

        verify(resourcesFiles).getGroups();
    }
}
