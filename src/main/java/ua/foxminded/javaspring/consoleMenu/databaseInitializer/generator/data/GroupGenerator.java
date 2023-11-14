package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.data;

import org.springframework.beans.factory.annotation.Autowired;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.DataConduct;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.consoleMenu.model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupGenerator implements DataGenerator<Group> {

    private ResourcesFilesDatabaseData resourcesFiles;
    private DataConduct dataConduct;

    private List<Group> groups = new ArrayList<>();

    @Autowired
    public GroupGenerator(ResourcesFilesDatabaseData resourcesFiles, DataConduct dataConduct) {
        this.resourcesFiles = resourcesFiles;
        this.dataConduct = dataConduct;
    }

    @Override
    public List<Group> generate() {
        List<String> groupNames = resourcesFiles.getGroups();

        Long groupID = 1L;

        for (String string : groupNames) {
            groups.add(new Group(groupID, string));
            groupID++;
        }
        dataConduct.setGroups(groups);
        return groups;
    }
}
