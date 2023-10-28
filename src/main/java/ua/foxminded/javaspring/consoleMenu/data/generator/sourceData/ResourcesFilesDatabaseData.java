package ua.foxminded.javaspring.consoleMenu.data.generator.sourceData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.consoleMenu.data.ReadResourcesFile;

import java.util.List;

@Component
public class ResourcesFilesDatabaseData {

    private ReadResourcesFile readFile;

    @Autowired
    public ResourcesFilesDatabaseData(ReadResourcesFile readFile) {
        this.readFile = readFile;
    }

    @Value("${databaseResourceFilepath.GROUPS_FILE}")
    private String GROUPS_FILE;

    @Value("${databaseResourceFilepath.COURSES_FILE}")
    private String COURSES_FILE;

    @Value("${databaseResourceFilepath.FIRST_NAME_FILE}")
    private String FIRST_NAME_FILE;

    @Value("${databaseResourceFilepath.LAST_NAME_FILE}")
    private String LAST_NAME_FILE;

    public List<String> getGroupsFilePath() {
        return getResourceData(GROUPS_FILE);
    }

    public List<String> getCoursesFilePath() {
        return getResourceData(COURSES_FILE);
    }

    public List<String> getFirstNameFilePath() {
        return getResourceData(FIRST_NAME_FILE);
    }

    public List<String> getLastNameFilePath() {
        return getResourceData(LAST_NAME_FILE);
    }

    private List<String> getResourceData(String filePath){
        return readFile.getData(filePath);
    }
}
