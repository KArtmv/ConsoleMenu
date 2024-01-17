package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data.GroupGenerator;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Group;
import ua.foxminded.javaspring.consoleMenu.pattern.InitializeObject;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GroupGeneratorTest {

    @Mock
    ResourcesFilesDatabaseData resourcesFiles;
    @Mock
    DataConduct dataConduct;

    @InjectMocks
    private GroupGenerator groupGenerator;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generate_shouldReturnListOfGroup_whenProvidedDataIsValid() {
        List<Group> expect = new InitializeObject().groupsListInit();

        when(resourcesFiles.getGroups()).thenReturn(Arrays.asList("groupName1", "groupName2", "groupName3"));

        List<Group> result = groupGenerator.generate();
        assertThat(result).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expect);

        verify(resourcesFiles).getGroups();
        verify(dataConduct).setGroups(result);
    }
}
