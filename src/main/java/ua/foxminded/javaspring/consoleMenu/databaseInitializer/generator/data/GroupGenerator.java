package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Group;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GroupGenerator implements DataGenerator<Group> {

    private ResourcesFilesDatabaseData resourcesFiles;
    private DataConduct dataConduct;

    @Autowired
    public GroupGenerator(ResourcesFilesDatabaseData resourcesFiles, DataConduct dataConduct) {
        this.resourcesFiles = resourcesFiles;
        this.dataConduct = dataConduct;
    }

    @Override
    public List<Group> generate() {
        List<String> groupNames = resourcesFiles.getGroups();

        List<Group> groups = IntStream.range(0, groupNames.size())
                .mapToObj(i -> new Group((long) (i + 1), groupNames.get(i)))
                .collect(Collectors.toList());

        dataConduct.setGroups(groups);
        return groups;
    }
}
