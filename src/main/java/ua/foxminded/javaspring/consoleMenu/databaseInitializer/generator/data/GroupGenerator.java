package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Group;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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

        List<Group> groups = groupNames.stream()
                .map(Group::new)
                .collect(Collectors.toList());

        dataConduct.setGroups(groups);
        return groups;
    }
}
