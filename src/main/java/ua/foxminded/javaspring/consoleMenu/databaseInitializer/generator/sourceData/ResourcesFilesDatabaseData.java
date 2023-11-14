package ua.foxminded.javaspring.consoleMenu.databaseInitializer.generator.sourceData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.databaseInitializer.ReadResourcesFile;

import java.util.List;

@Component
public class ResourcesFilesDatabaseData {

    private ReadResourcesFile readFile;

    @Autowired
    public ResourcesFilesDatabaseData(ReadResourcesFile readFile) {
        this.readFile = readFile;
    }

    @Value("${sourceData.DataFilePath.GROUPS_FILE}")
    private String GROUPS_FILE;

    @Value("${sourceData.DataFilePath.COURSES_FILE}")
    private String COURSES_FILE;

    @Value("${sourceData.DataFilePath.FIRST_NAME_FILE}")
    private String FIRST_NAME_FILE;

    @Value("${sourceData.DataFilePath.LAST_NAME_FILE}")
    private String LAST_NAME_FILE;

    public List<String> getGroups() {
        return getSourceData(GROUPS_FILE);
    }

    public List<String> getCourses() {
        return getSourceData(COURSES_FILE);
    }

    public List<String> getFirstNames() {
        return getSourceData(FIRST_NAME_FILE);
    }

    public List<String> getLastNames() {
        return getSourceData(LAST_NAME_FILE);
    }

    private List<String> getSourceData(String filePath) {
        return readFile.getData(filePath);
    }
}
