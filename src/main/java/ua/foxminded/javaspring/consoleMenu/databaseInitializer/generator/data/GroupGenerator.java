package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupGenerator {

    private ResourcesFilesDatabaseData resourcesFiles;

    private List<Group> groups = new ArrayList<>();

    @Autowired
    public GroupGenerator(ResourcesFilesDatabaseData resourcesFiles) {
        this.resourcesFiles = resourcesFiles;
    }

    public List<Group> generate() {
        List<String> groupNames = resourcesFiles.getGroups();

        Long groupID = 1L;

        for (String string : groupNames) {
            groups.add(new Group(groupID, string));
            groupID++;
        }
        return groups;
    }
}
